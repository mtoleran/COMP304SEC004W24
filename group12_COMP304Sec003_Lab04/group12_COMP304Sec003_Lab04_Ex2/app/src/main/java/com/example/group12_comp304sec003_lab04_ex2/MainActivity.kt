package com.example.group12_comp304sec003_lab04_ex2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.group12_comp304sec003_lab04_ex2.ui.theme.Group12_COMP304Sec003_Lab04_Ex2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Group12_COMP304Sec003_Lab04_Ex2Theme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    var shouldShowSoftwareEngineeringDetails by rememberSaveable { mutableStateOf(false) }
    var shouldShowHealthInformaticsDetails by rememberSaveable { mutableStateOf(false) }
    var shouldShowGameProgrammingDetails by rememberSaveable { mutableStateOf(false) }

    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        if (shouldShowOnboarding) {
            OnboardingScreen(
                onAISoftwareEngineeringClicked = {
                    shouldShowSoftwareEngineeringDetails = true
                    shouldShowOnboarding = false
                },

                onSoftwareEngineeringTechnicianClicked = {
                    shouldShowHealthInformaticsDetails = true
                    shouldShowOnboarding = false
                },
                onSoftwareEngineeringTechnologyClicked = {
                    shouldShowGameProgrammingDetails = true
                    shouldShowOnboarding = false
                },
            )
        } else {
            if (shouldShowSoftwareEngineeringDetails) {
                ProgramDetails(
                    modifier = Modifier.fillMaxSize(),
                    onBackButtonClicked = {
                        shouldShowSoftwareEngineeringDetails = false
                        shouldShowOnboarding = true
                    },
                    courses = listOf(
                        stringResource(R.string.comp214),
                        stringResource(R.string.comp216),
                        stringResource(R.string.comp247),
                        stringResource(R.string.comp254),
                        stringResource(R.string.comp311),
                        stringResource(R.string.engl253)
                    )
                )
            } else if (shouldShowHealthInformaticsDetails) {
                ProgramDetails(
                    modifier = Modifier.fillMaxSize(),
                    onBackButtonClicked = {
                        shouldShowHealthInformaticsDetails = false
                        shouldShowOnboarding = true
                    },
                    courses = listOf(
                        stringResource(R.string.cnet307),
                        stringResource(R.string.comp212),
                        stringResource(R.string.comp231),
                        stringResource(R.string.comp311),
                        stringResource(R.string.comp318),
                        stringResource(R.string.emps101),
                        stringResource(R.string.gned)
                    )
                )
            } else if (shouldShowGameProgrammingDetails){
                ProgramDetails(
                    modifier = Modifier.fillMaxSize(),
                    onBackButtonClicked = {
                        shouldShowGameProgrammingDetails = false
                        shouldShowOnboarding = true
                    },
                    courses = listOf(
                        stringResource(R.string.comp212),
                        stringResource(R.string.comp216),
                        stringResource(R.string.comp254),
                        stringResource(R.string.comp304),
                        stringResource(R.string.comp311),
                        stringResource(R.string.math210)
                    )
                )
            }
        }
    }
}

@Composable
fun OnboardingScreen(
    onAISoftwareEngineeringClicked: () -> Unit,
    onSoftwareEngineeringTechnicianClicked: () -> Unit,
    onSoftwareEngineeringTechnologyClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.selectProgram))
        Button(
            modifier = Modifier.padding(vertical = 10.dp),
            onClick = { onAISoftwareEngineeringClicked() }
        ) {
            Text(stringResource(R.string.AISoftEng))
        }
        Button(
            modifier = Modifier.padding(vertical = 10.dp),
            onClick = { onSoftwareEngineeringTechnicianClicked() }
        ) {
            Text(stringResource(R.string.softEngTechnician))
        }
        Button(
            modifier = Modifier.padding(vertical = 10.dp),
            onClick = onSoftwareEngineeringTechnologyClicked
        ) {
            Text(stringResource(R.string.softEngTechnology))
        }
    }
}

@Composable
private fun ProgramDetails(
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    courses: List<String>,
){
    Column(
        modifier = modifier.padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        LazyColumn(Modifier.weight(1f)) {
            items(items = courses) { name ->
                ProgramCourse(name = name)
            }
        }
        Button(
            modifier = Modifier.padding(vertical = 75.dp),
            onClick = { onBackButtonClicked() }
        ) {
            Text(stringResource(R.string.backToPrograms))
        }
    }

}

// Greeting/Program Course
@Composable
private fun ProgramCourse(name: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
private fun CardContent(name: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    val contentText = when (name) {
        stringResource(R.string.comp212) -> stringResource(R.string.comp212Desc)
        stringResource(R.string.comp214) -> stringResource(R.string.comp214Desc)
        stringResource(R.string.comp216) -> stringResource(R.string.comp216Desc)
        stringResource(R.string.comp231) -> stringResource(R.string.comp231Desc)
        stringResource(R.string.comp247) -> stringResource(R.string.comp247Desc)
        stringResource(R.string.comp254) -> stringResource(R.string.comp254Desc)
        stringResource(R.string.comp304) -> stringResource(R.string.comp212Desc)
        stringResource(R.string.comp311) -> stringResource(R.string.comp311Desc)
        stringResource(R.string.comp318) -> stringResource(R.string.comp318Desc)
        stringResource(R.string.emps101) -> stringResource(R.string.emps101Desc)
        stringResource(R.string.gned) -> stringResource(R.string.gnedDesc)
        stringResource(R.string.math210) -> stringResource(R.string.math210Desc)
        stringResource(R.string.engl253) -> stringResource(R.string.engl253Desc)
        stringResource(R.string.cnet307) -> stringResource(R.string.cnet307Desc)

        else -> ("Composem ipsum color sit lazy, " +
                "padding theme elit, sed do bouncy. ").repeat(4)
    }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = name, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = contentText,
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Filled.ExpandLess else Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }

    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    Group12_COMP304Sec003_Lab04_Ex2Theme {
        OnboardingScreen(
            onAISoftwareEngineeringClicked = {},
            onSoftwareEngineeringTechnicianClicked = {},
            onSoftwareEngineeringTechnologyClicked = {},
        )
    }
}

@Preview
@Composable
fun MyAppPreview() {
    Group12_COMP304Sec003_Lab04_Ex2Theme {
        MyApp(Modifier.fillMaxSize())
    }
}