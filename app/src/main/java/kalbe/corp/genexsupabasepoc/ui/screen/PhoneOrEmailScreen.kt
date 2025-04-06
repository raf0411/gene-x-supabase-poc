package kalbe.corp.genexsupabasepoc.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kalbe.corp.genexsupabasepoc.R
import kalbe.corp.genexsupabasepoc.navigation.Routes

@Composable
fun PhoneOrEmailScreen(
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.grey)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            painter = painterResource(id = R.drawable.dna),
            contentDescription = null,
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
                .background(color = colorResource(id = R.color.white)),
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Choose Login Method", fontWeight = FontWeight.Bold, fontSize = 24.sp)

                Spacer(Modifier.height(48.dp))

                Button(
                    onClick = {
                        navController.navigate(Routes.PhoneLoginScreen)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "Phone", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(Modifier.width(16.dp))
                        Icon(Icons.Default.Phone, contentDescription = null, tint = Color.White)
                    }
                }

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.navigate(Routes.EmailLoginScreen)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Row {
                        Text(text = "Email", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(Modifier.width(16.dp))
                        Icon(Icons.Default.Mail, contentDescription = null, tint = Color.White)
                    }
                }
            }
        }
    }
}