package kalbe.corp.genexsupabasepoc.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.outlined.Female
import androidx.compose.material.icons.outlined.Male
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kalbe.corp.genexsupabasepoc.R

@Composable
fun ProfileBanner(
    profileImage: String,
    profileName: String,
    profileEmail: String,
    profilePhone: String,
    profileGender: String,
    profileAge: Int,
    onSwitchAccountClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(colorResource(id = R.color.light_grey))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if(profileImage == ""){
                Image(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(120.dp),
                    painter = painterResource(R.drawable.user_profile),
                    contentDescription = "User Profile Picture",
                )
            } else {
                when (profileImage){
                    else -> {
                        AsyncImage(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(120.dp),
                            model = profileImage,
                            contentDescription = "User Profile Picture",
                        )
                    }
                }

            }


            Spacer(modifier = Modifier.height(12.dp))
            Text(text = profileName, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = profileEmail)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (profileGender == "Male"){
                    Icon(
                        imageVector = Icons.Outlined.Male,
                        contentDescription = "Gender",
                    )
                } else if(profileGender == "Female"){
                    Icon(
                        imageVector = Icons.Outlined.Female,
                        contentDescription = "Gender",
                    )
                } else{
                    Icon(
                        imageVector = Icons.Outlined.Male,
                        contentDescription = "Gender",
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "$profileAge, $profileGender")
            }
        }

        IconButton(
            onClick = { onSwitchAccountClick() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.SwapHoriz,
                contentDescription = "Switch Account",
            )
        }
    }
}