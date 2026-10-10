package com.ashenmajo.pickupcode

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.ashenmajo.pickupcode.ui.theme.PickUpCodeTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            PickUpCodeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(), bottomBar = {
                        Box(
                            modifier = Modifier.padding(bottom = 20.dp).fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                "by ashenmajo",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.clickable {
                                    context.startActivity(
                                            Intent(Intent.ACTION_VIEW,"https://github.com/ashenmajo/PickUpCode".toUri())
                                        )
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
data class Brand(
    val icon:String,
    val name:String,
    val color: Color,
    val appUris: List<String>,
    val webUri: String
)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val pddColor=Color(0xFFE02E24)
    val tbColor=Color(0xFFFF5000)
    val cnColor=Color(0xFF1677FF)

    val brands=listOf(
        Brand("拼","拼多多",pddColor
            , listOf(
                "pinduoduo://com.xunmeng.pinduoduo/mdkd/package?tab=ID_CODE&entry_source=11&refer_page_name=login&refer_page_sn=10169",
                "pinduoduo://com.xunmeng.pinduoduo/mdkd/package?tab=ID_CODE&entry_source=11",
                "pinduoduo://com.xunmeng.pinduoduo/mdkd/package")
            ,"https://m.pinduoduo.net/mdkd/package?entry_source=18&extra_params=from_wx_jump%3D1&p_channel=0"
        ),
        Brand("淘","淘宝",tbColor
            ,listOf(
                "taobao://m.taobao.com/tbopen/index.html?h5Url=https%3A%2F%2Fpages-fast.m.taobao.com%2Fwow%2Fz%2Funiapp%2F1011717%2Flast-mile-fe%2Fend-collect-platform%2Fidentity-code%3Fx-ssr%3Dtrue",
                "tbopen://m.taobao.com/tbopen/index.html?h5Url=https%3A%2F%2Fpages-fast.m.taobao.com%2Fwow%2Fz%2Funiapp%2F1011717%2Flast-mile-fe%2Fend-collect-platform%2Fidentity-code%3Fx-ssr%3Dtrue")
            ,   "https://pages-fast.m.taobao.com/wow/z/uniapp/1011717/last-mile-fe/end-collect-platform/identity-code?x-ssr=true"),
        Brand("菜","菜鸟驿站",cnColor
            ,listOf( "cainiao://desktop/station_code")
            ,"https://market.m.taobao.com/app/cn-yz/multi-activity/authCode.html?bizEntry=ALIPAY_GUOGUO"),
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "PickUpCode",
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "身份码",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        brands.forEach { brand -> BrandCard(brand) }

    }
}
@Composable
fun BrandCard(brand: Brand){
    //val screenHeight=LocalConfiguration.current.screenHeightDp.dp
    val context= LocalContext.current
    Card(
        onClick = {
          if (!openBrand(context,brand.appUris,brand.webUri)){
              Toast.makeText(context,"打开失败", Toast.LENGTH_SHORT).show()
          } },
        modifier = Modifier.fillMaxWidth().height(77.dp),
        colors = CardDefaults.cardColors(
            brand.color.copy(alpha = 0.1f).compositeOver(MaterialTheme.colorScheme.surface)
        ),
    ) {
        Row (
            modifier= Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ){
            Box(
                modifier = Modifier
                    .background(brand.color, RoundedCornerShape(12.dp))
                    .size(40.dp)
                    .padding(horizontal = 6.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = brand.icon,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(modifier= Modifier.width(16.dp))
            Text(
                text = brand.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}
fun openUri(context: Context,uri: String): Boolean{
    return try {
        context.startActivity(Intent(Intent.ACTION_VIEW,uri.toUri()))
       true
    }catch (e: Exception){
       false
    }
}
fun openBrand(context: Context,appUris:List<String>,webUri:String): Boolean{
    for(uri in appUris){
        if (openUri(context,uri)) return true
    }
    return openUri(context,webUri)
}