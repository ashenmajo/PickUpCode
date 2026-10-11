package com.ashenmajo.pickupcode

import androidx.compose.ui.graphics.Color

data class Brand(
    val icon: String,
    val name: String,
    val color: Color,
    val appUris: List<String>,
    val webUri: String
)

object BrandsData {
    val pddColor = Color(0xFFE02E24)
    val tbColor = Color(0xFFFF5000)
    val cnColor = Color(0xFF1677FF)

    val brands = listOf(
        Brand(
            "拼", "拼多多", pddColor,
            listOf(
                "pinduoduo://com.xunmeng.pinduoduo/mdkd/package?tab=ID_CODE&entry_source=11&refer_page_name=login&refer_page_sn=10169",
                "pinduoduo://com.xunmeng.pinduoduo/mdkd/package?tab=ID_CODE&entry_source=11",
                "pinduoduo://com.xunmeng.pinduoduo/mdkd/package"
            ),
            "https://m.pinduoduo.net/mdkd/package?entry_source=18&extra_params=from_wx_jump%3D1&p_channel=0"
        ),
        Brand(
            "淘", "淘宝", tbColor,
            listOf(
                "taobao://m.taobao.com/tbopen/index.html?h5Url=https%3A%2F%2Fpages-fast.m.taobao.com%2Fwow%2Fz%2Funiapp%2F1011717%2Flast-mile-fe%2Fend-collect-platform%2Fidentity-code%3Fx-ssr%3Dtrue",
                "tbopen://m.taobao.com/tbopen/index.html?h5Url=https%3A%2F%2Fpages-fast.m.taobao.com%2Fwow%2Fz%2Funiapp%2F1011717%2Flast-mile-fe%2Fend-collect-platform%2Fidentity-code%3Fx-ssr%3Dtrue"
            ),
            "https://pages-fast.m.taobao.com/wow/z/uniapp/1011717/last-mile-fe/end-collect-platform/identity-code?x-ssr=true"
        ),
        Brand(
            "菜", "菜鸟驿站", cnColor,
            listOf("cainiao://desktop/station_code"),
            "https://market.m.taobao.com/app/cn-yz/multi-activity/authCode.html?bizEntry=ALIPAY_GUOGUO"
        ),
    )
}