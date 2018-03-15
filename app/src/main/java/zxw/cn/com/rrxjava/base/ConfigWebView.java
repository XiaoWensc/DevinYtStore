package zxw.cn.com.rrxjava.base;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;

import devin.cn.com.rxjavaretrofit.mvp.BaseActivity;
import zxw.cn.com.rrxjava.ui.webview.BaseWebViewActivity;
import zxw.cn.com.rrxjava.utils.ToolCookie;

/**
 * Created by zengxiaowen on 2018/1/29.
 */

public class ConfigWebView {

    public static void shouldOverrideUrlLoading( WebView view,String url){
        String id = Uri.parse(url).getQueryParameter("productId");// 产品Id
        String skuId = Uri.parse(url).getQueryParameter("skuId");// 产品Id
        String dimensionId = Uri.parse(url).getQueryParameter("dimensionId"); //分类ID
        String promotionId = Uri.parse(url).getQueryParameter("promotionId"); //促销ID
        String shopId = Uri.parse(url).getQueryParameter("shopId"); //店铺ID
        if (id != null && !id.isEmpty()) {   //
            ((BaseActivity) view.getContext()).getOperation().addParameter("productId", id);
            ((BaseActivity) view.getContext()).getOperation().addParameter("skuId", skuId);
//            ((BaseActivity) view.getContext()).getOperation().forward(ItemAtivity.class);
        } else if (dimensionId != null || promotionId != null || shopId != null) {
            ((BaseActivity) view.getContext()).getOperation().addParameter("dimensionId", dimensionId);
            ((BaseActivity) view.getContext()).getOperation().addParameter("promotionId", promotionId);
            ((BaseActivity) view.getContext()).getOperation().addParameter("shopId", shopId);
//            ((BaseActivity) view.getContext()).getOperation().forward(SearchListActivity.class);
        }
//        else if (url.contains(ClientUtil.ITBT())) {
//            final String uri = "cn.yt.itbt.client";
//            // 打开雅堂金融
//            if (Constant.isAppInstalled(view.getContext(), uri)) {
//                Constant.startApplication(uri, view.getContext());
//            } else {// 跳转到微信web页面
//                Uri uri1 = Uri.parse(url);
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri1);
//                view.getContext().startActivity(intent);
//            }
//        } else if (url.contains(ClientUtil.entryCouponsCenter())) {
//            ((BaseActivity) view.getContext()).getOperation().forward(CouponsActivity.class);
//        } else if (url.contains(ClientUtil.entryBrands())) {// 品牌汇
//            ((BaseActivity) view.getContext()).getOperation().forward(BrandGatherActivity.class);
//            return true;
//        } else if (url.contains(ClientUtil.shakeShake())) { // 摇一摇
//            ((BaseActivity) view.getContext()).getOperation().forward(ShakeHomeActivity.class);
//        } else if (url.contains("pay/success")) {
//            Constant.isValidSuperMember = true;
//            view.getContext().sendBroadcast(new Intent(ActionName.GOACCOUNTACTIVITY));
//            ((BaseActivity) view.getContext()).finish();
//        }
        else if (url.contains("weixin://wap/pay")) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            view.getContext().startActivity(intent);
            //成功支付頁面
        }
//        else if (url.contains("/myCenter/orderCenter.html")) { //我的订单
//            ((BaseActivity) view.getContext()).getOperation().forward(MyOrderAtcivity.class);
//            ((BaseActivity) view.getContext()).finish();
//        }
//        else if (url.contains("/index.html")) {
//            if (url.contains("/svip/index.html")){
//                ((BaseActivity) view.getContext()).getOperation().forward(ShopGoodsActivity.class);  //超级会员店铺
//            }else {
//                ((BaseActivity) view.getContext()).getOperation().forward(MainActivity.class); // 返回首页
//                ((BaseActivity) view.getContext()).finish();
//            }
//        } else if (url.contains("/log/login.html")) {
//            ((BaseActivity) view.getContext()).getOperation().forward(LoginActivity.class);
//            ((BaseActivity) view.getContext()).finish();
//        }
//        else if (isInnerOpen) {
//            final PayTask task = new PayTask(((BaseActivity) view.getContext()));
//            boolean isIntercepted = task.payInterceptorWithUrl(url, true, new H5PayCallback() {
//                @Override
//                public void onPayResult(H5PayResultModel h5PayResultModel) {
//                    // 支付结果返回
//                    if (h5PayResultModel.getResultCode().equals("9000")) {
//                        Constant.isValidSuperMember = true;
//                        view.getContext().sendBroadcast(new Intent(ActionName.GOACCOUNTACTIVITY));
//                        ((BaseActivity) view.getContext()).finish();
//                    }
//                    final String url1 = h5PayResultModel.getReturnUrl();
//                    if (!TextUtils.isEmpty(url1)) {
//                        ((BaseActivity) view.getContext()).runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                view.loadUrl(url1);
//                            }
//                        });
//                    } else {
//                        ((BaseActivity) view.getContext()).runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                view.goBack();
//                                ToolToast.showLong(view.getContext(), "支付失败");
//                            }
//                        });
//                    }
//                }
//            });
//            if (!isIntercepted) {
//
//                view.loadUrl(url);
//            }
//        }
        else {
            ToolCookie.removeSessionCookie();
            ((BaseActivity) view.getContext()).getOperation().addParameter("link", url);
            ((BaseActivity) view.getContext()).getOperation().forward(BaseWebViewActivity.class);
        }
    }
}
