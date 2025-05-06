package com.fahrulredho0018.assessment02.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fahrulredho0018.assessment02.ui.theme.Assessment02Theme
import com.fahrulredho0018.assessment02.R
import com.fahrulredho0018.assessment02.util.ViewModelFactory

const val KEY_ID_BUKU = "idBuku"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null) {
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    var judul by remember { mutableStateOf("") }
    var penulis by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getPenulis(id) ?: return@LaunchedEffect
        judul = data.judul
        penulis = data.nama
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if (id == null)
                        Text(text = stringResource(id = R.string.tambah_penulis))
                    else
                        Text(text = stringResource(id = R.string.edit_penulis))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        if (judul == "" || penulis == "") {
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }

                        if (id == null) {
                            viewModel.insert(judul, penulis)
                        } else {
                            viewModel.update(id, judul,penulis)
                        }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (id != null) {
                        DeleteAction {
                            viewModel.delete(id)
                            navController.popBackStack()
                        }
                    }
                }
            )
        }
    ) { padding ->
        FormCatatan(
            title = judul,
            onTitleChange = {judul = it},
            desc = penulis,
            onDescChange = {penulis = it},
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormCatatan(
    title: String, onTitleChange: (String) -> Unit,
    desc: String, onDescChange: (String) -> Unit,
    modifier: Modifier
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { onTitleChange(it)},
            label = { Text(text = stringResource(R.string.judul)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = desc,
            onValueChange = { onDescChange(it)},
            label = { Text(text = stringResource(R.string.nama)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun DeleteAction(delete: () -> Unit) {
    var expanded  by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false}
        ) {
            DropdownMenuItem(
                text = { Text(text = stringResource(id = R.string.hapus))
                },
                onClick = {
                    expanded = false
                    delete()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    Assessment02Theme {
        DetailScreen(rememberNavController())
    }
}
