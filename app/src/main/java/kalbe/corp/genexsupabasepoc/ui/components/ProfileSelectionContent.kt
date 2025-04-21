package kalbe.corp.genexsupabasepoc.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kalbe.corp.genexsupabasepoc.models.Profile

@Composable
fun ProfileSelectionContent(
    profiles: List<Profile>,
    selectedProfileId: String,
    onProfileClick: (profileId: String) -> Unit,
    onCloseClick: () -> Unit,
    onAddAccountClick: () -> Unit,
) {
    var profileList by remember { mutableStateOf<List<Profile>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(key1 = Unit) {
        isLoading = true
        errorMessage = null

        try {
            profileList = profiles

        } catch (e: Exception) {
            Log.e("ProfileScreenError", "Failed to fetch profiles", e)
            errorMessage = "Failed to load profiles: ${e.message}"
        } finally {
            isLoading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Switch User",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Select an account to continue",
            )

            when {
                isLoading -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }
                errorMessage != null -> {
                    Text("Error: $errorMessage")
                }
                profileList.isEmpty() -> {
                    Text("No profiles found.")
                }
                else -> {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .heightIn(max = 350.dp),
                        columns = GridCells.Fixed(1),
                        contentPadding = PaddingValues(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {

                        items(profileList) { profile ->
                            ProfileItem(
                                profile = profile,
                                isSelected = profile.id == selectedProfileId,
                                onProfileClick = { onProfileClick(profile.id) },
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = { onAddAccountClick() },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 12.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Add New Account",
                        fontSize = 16.sp,
                    )
                }
            }
        }

        IconButton(
            onClick = { onCloseClick() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 0.dp, end = 0.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileSelectionContentPreview() {
    val profiles = listOf<Profile>()

    ProfileSelectionContent(
        onProfileClick = {},
        onCloseClick = {},
        onAddAccountClick = {},
        profiles = profiles,
        selectedProfileId = "random_id",
    )
}