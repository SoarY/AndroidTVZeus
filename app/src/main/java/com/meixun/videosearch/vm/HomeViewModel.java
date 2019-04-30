package com.meixun.videosearch.vm;

import android.app.Application;
import android.databinding.ObservableField;

import com.annimon.stream.Stream;
import com.meixun.videosearch.adapter.HomeAdapter;
import com.meixun.videosearch.base.BaseViewModel;
import com.meixun.videosearch.bean.AndroidBean;
import com.meixun.videosearch.bean.AndroidPlayBannerBean;
import com.meixun.videosearch.constant.ConstantsImageUrl;
import com.meixun.videosearch.constant.ItemStyle;
import com.meixun.videosearch.retrofit.APIException;
import com.meixun.videosearch.retrofit.HttpResultFunc;
import com.meixun.videosearch.retrofit.MyObserver;
import com.meixun.videosearch.retrofit.RetrofitClient;
import com.meixun.videosearch.retrofit.ServerResultFunc;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * NAME：YONG_
 * Created at: 2018/12/26
 * Describe:
 */
public class HomeViewModel extends BaseViewModel {

    /**
     * 直接xml中dataBing引入
     */
    public ObservableField<List<String>> bannerUrlDatas = new ObservableField<>();
    public ObservableField<List<String>> bannerTitleDatas = new ObservableField<>();

    public HomeAdapter adapter = new HomeAdapter();

    public HomeViewModel(Application application) {
        super(application);
    }

    /**
     * 网络请求样板展示
     */
    public void getSampleData() {
        RetrofitClient instance = RetrofitClient.getInstance();
        Observable<List<AndroidPlayBannerBean>> observable = instance.getApi().getAndroidPlayBanner()
                .map(new ServerResultFunc<>())
                .onErrorResumeNext(new HttpResultFunc<>());
        instance.toSubscribe(getLifecycleProvider(), observable, new MyObserver<List<AndroidPlayBannerBean>>() {

            @Override
            public void onNext(List<AndroidPlayBannerBean> data) {
                List<String> titles = Stream.of(data).map(bean -> bean.title).toList();
                List<String> urls = Stream.of(data).map(bean -> bean.imagePath).toList();
                bannerTitleDatas.set(titles);
                bannerUrlDatas.set(urls);
            }

            @Override
            protected void onError(APIException ex) {
            }
        });
    }


    public void getHomeData() {

        List<AndroidBean> childDatas1 = new ArrayList<AndroidBean>() {
            {
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_556_370, ConstantsImageUrl.Home_556_370_1));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_556_370, ConstantsImageUrl.Home_556_370_2));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_1));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_2));
            }
        };

        List<AndroidBean> childDatas2 = new ArrayList<AndroidBean>() {
            {
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_3));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_4));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_5));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_6));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_7));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_8));
            }
        };

        List<AndroidBean> childDatas3 = new ArrayList<AndroidBean>() {
            {
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_9));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_10));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_11));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_12));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_13));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_14));
            }
        };

        List<AndroidBean> childDatas4 = new ArrayList<AndroidBean>() {
            {
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_1740_250, ConstantsImageUrl.HOME_1740_250_1));
            }
        };

        List<AndroidBean> childDatas5 = new ArrayList<AndroidBean>() {
            {
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_556_370, ConstantsImageUrl.Home_556_370_3));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_556_370, ConstantsImageUrl.Home_556_370_4));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_1));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_2));
            }
        };

        List<AndroidBean> childDatas6 = new ArrayList<AndroidBean>() {
            {
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_1));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_2));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_3));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_4));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_5));
                add(new AndroidBean(ItemStyle.Style.ADAPTER, ItemStyle.Size.S_260_370, ConstantsImageUrl.HOME_380_540_6));
            }
        };

        List<List<AndroidBean>> datas = new ArrayList<List<AndroidBean>>() {
            {
                add(childDatas1);
                add(childDatas2);
                add(childDatas3);
                add(childDatas4);
                add(childDatas5);
                add(childDatas6);
            }
        };

        adapter.setData(datas);
    }

}
