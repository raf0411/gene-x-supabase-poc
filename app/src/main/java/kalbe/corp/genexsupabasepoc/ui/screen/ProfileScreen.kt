package kalbe.corp.genexsupabasepoc.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen() {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Account",
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            fontSize = 24.sp, fontWeight = FontWeight.Bold
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text("User123", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("user@email.com")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Phone: +6200000001")
            Spacer(modifier = Modifier.height(12.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}