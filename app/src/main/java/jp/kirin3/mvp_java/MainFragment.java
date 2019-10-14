package jp.kirin3.mvp_java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.google.common.base.Preconditions.checkNotNull;


public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter mPresenter;
    private TextView mTextView1;
    private ImageView mImageView1;
    private Button mButton1;


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mTextView1 = root.findViewById(R.id.textView1);
        mImageView1 = root.findViewById(R.id.imageView1);
        mButton1 = root.findViewById(R.id.button1);

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addCount();
                mPresenter.start();
            }
        });

        return root;
    }

    // 表示関係の関数をオーバーライドしている
    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showTextMsg(String word){
        mTextView1.setText(word);
    }

    @Override
    public void changeImage(){
        if(mImageView1.getVisibility() == View.VISIBLE) mImageView1.setVisibility(View.GONE);
        else mImageView1.setVisibility(View.VISIBLE);
    }

}
