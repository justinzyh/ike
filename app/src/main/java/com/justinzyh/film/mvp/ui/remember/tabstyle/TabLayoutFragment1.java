package com.justinzyh.film.mvp.ui.remember.tabstyle;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.justinzyh.film.R;
import com.justinzyh.film.mvp.utils.FileUtil;
import com.justinzyh.film.mvp.utils.custom_view.InterceptLinearLayout;
import com.justinzyh.film.mvp.utils.custom_view.RichTextEditor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static com.justinzyh.film.R.id.line_intercept;


/**
 * Created by justinzyh on 2016/11/7.
 */

public class TabLayoutFragment1 extends Fragment implements View.OnClickListener {

    private final String TAG                         = "RichTextActivity";
    private final int    REQUEST_CODE_CAPTURE_CAMEIA = 100;
    private final int    REQUEST_CODE_PICK_IMAGE     = 200;
    private boolean isKeyBoardUp, isEditTouch;// 判断软键盘的显示与隐藏
    private File     mCameraImageFile;// 照相机拍照得到的图片
    private FileUtil mFileUtils;

    private EditText              etNameT1;
    private RichTextEditor        rtEditor1;
    private ImageView             addPic;
    private ImageView             takePic;
    private TextView              commitPic;
    private InterceptLinearLayout mIntercept;
    private View mView;
    private LinearLayout line_addImg;
    private LinearLayout line_rootView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                        | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mFileUtils = new FileUtil(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_textremark_tablayout1, null);
        initView();
        initData();
        return mView;
    }

    private void initView() {
        etNameT1 = (EditText) mView.findViewById(R.id.et_name_tab1);
        rtEditor1 = (RichTextEditor) mView.findViewById(R.id.rich_text_tab1);
        addPic = (ImageView) mView.findViewById(R.id.img_addPicture);
        takePic = (ImageView) mView.findViewById(R.id.img_takePicture);
        commitPic = (TextView) mView.findViewById(R.id.img_commitPic);
        mIntercept = (InterceptLinearLayout) mView.findViewById(line_intercept);
        line_addImg = (LinearLayout) mView.findViewById(R.id.line_addImg);
        line_rootView = (LinearLayout)mView.findViewById(R.id.line_rootView);
        addPic.setOnClickListener(this);
        takePic.setOnClickListener(this);
        mIntercept.setIntercept(true);
        rtEditor1.setIntercept(true);
    }

    private void initData() {
        etNameT1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isEditTouch = false;
                    line_addImg.setVisibility(View.GONE);
                }
            }

        });
        rtEditor1.setLayoutClickListener(new RichTextEditor.LayoutClickListener() {
            @Override
            public void layoutClick() {
                isEditTouch = true;
                line_addImg.setVisibility(View.VISIBLE);
            }
        });

        line_rootView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int heightDiff = line_rootView.getRootView()
                                .getHeight() - line_rootView.getHeight();
                        if (isEditTouch) {
                            if (heightDiff > 500) {// 大小超过500时，一般为显示虚拟键盘事件,此判断条件不唯一
                                isKeyBoardUp = true;
                               line_addImg.setVisibility(View.VISIBLE);
                            } else {
                                if (isKeyBoardUp) {
                                    isKeyBoardUp = false;
                                    isEditTouch = false;
                                    line_addImg.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_addPicture:
                // 打开系统相册
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");// 相片类型
                startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
                break;
            case R.id.img_takePicture:
                // 打开相机
                openCamera();
                break;
            case R.id.img_commitPic:
                // 提交
                break;

        }
    }

    private void openCamera() {
        try {
            File photo_dir = new File(mFileUtils.getStorageDirectory());
            if (!photo_dir.exists())
                photo_dir.mkdirs();// 创建照片的存储目录

            mCameraImageFile = new File(photo_dir, getPhotoFileName());// 给新照的照片文件命名
            final Intent intent = getTakePickIntent(mCameraImageFile);
            startActivityForResult(intent, REQUEST_CODE_CAPTURE_CAMEIA);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Intent getTakePickIntent(File file) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        return intent;
    }

    /**
     * 用当前时间给取得的图片命名
     */
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyy_MM_dd_HH_mm_ss");
        return dateFormat.format(date) + ".jpg";
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
            Uri uri = data.getData();
            rtEditor1.insertImage(mFileUtils.getFilePathFromUri(uri));
        } else if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA
                && resultCode == RESULT_OK) {
            rtEditor1.insertImage(mCameraImageFile.getAbsolutePath());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFileUtils.deleteRichTextImage();
    }
}