package kalbe.corp.genexsupabasepoc.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kalbe.corp.genexsupabasepoc.R
import kalbe.corp.genexsupabasepoc.models.Profile

@Composable
fun ProfileItem(
    profile: Profile,
    onProfileClick: () -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(12.dp)
            )
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onProfileClick)
            .padding(16.dp)
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
                Image(
                    modifier = Modifier.width(50.dp),
                    painter = painterResource(id = R.drawable.user_profile),
                    contentDescription = null,
                )

                Spacer(Modifier.width(8.dp))

                Column() {
                    Text(text = profile.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(text = "Male, 23")
                }
            }

            IconButton(
                onClick = {onProfileClick},
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileItemPreview(){
    val profile: Profile = Profile(
        id = "1",
        name = "Raffi",
        nutrition = "Needed",
        calories = 12,
        max_heart_rate = 15,
        protein_take = 15f,
        weight = 80.0f,
        height = 168.0f,
    )

    ProfileItem(
        profile = profile,
        onProfileClick = {},
    )
}