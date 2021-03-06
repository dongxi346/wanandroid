package com.dong.wanandroid.welfare;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dong.wanandroid.R;
import com.dong.wanandroid.base.BaseFragment;
import com.dong.wanandroid.data.welfare.WelfareModel;
import com.dong.wanandroid.http.ApiParamConstant;
import com.dong.wanandroid.util.tool.GridSpacingItemDecoration;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelfareFragment extends BaseFragment implements WelfareIView {

    private static final String TAG = "WelfareFragment";
    private static WelfareFragment sMWelfareFragment = null;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.loading_progress)
    ProgressBar loadingView;

    private WelfareAdapter mWelfareAdapter;
    private GridLayoutManager mGridLayoutManager;
    private int page = 1;
    private int size = 10;
    private ArrayList<WelfareModel> mWelfareModels = new ArrayList<>();
    private WelfareIpresenter mWelfareIpresenter;

    public WelfareFragment() {}

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_welfare;
    }

    @Override
    protected void initViewsAndEvents(View view) {}

    @Override
    protected void initData() {
        mWelfareIpresenter = new WelfareIpresenterCompl(this);
        mWelfareIpresenter.getWelfareData(getActivity(), ApiParamConstant.WELFARE, size, page);

        // ItemView
        mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        mWelfareAdapter = new WelfareAdapter(R.layout.welfare_item, mWelfareModels);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, DensityUtil.dp2px(9), true));
        mRecyclerView.setAdapter(mWelfareAdapter);
        mWelfareAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                //成功获取更多数据
                mWelfareIpresenter.getWelfareData(getActivity(), ApiParamConstant.WELFARE, size, page);
                mWelfareAdapter.loadMoreComplete();

            }
        }, mRecyclerView);

        mWelfareAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e(TAG, "onItemClick: ===================");
                mWelfareIpresenter.toBigImageAc(getActivity(), (ImageView) view.findViewById(R.id.iv_grid_welfare), mWelfareModels.get(position).getUrl());
            }
        });
    }

    @Override
    protected void onUserVisible() {}

    @Override
    protected void onUserInvisible() {}

    @Override
    protected void onPreDestroyView() {}

    @Override
    public void showLoadingView() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        loadingView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showWelfareResult(ArrayList<WelfareModel> welfareModelArrayList) {
        mWelfareModels.addAll(welfareModelArrayList);
        mWelfareAdapter.notifyDataSetChanged();
    }

}
