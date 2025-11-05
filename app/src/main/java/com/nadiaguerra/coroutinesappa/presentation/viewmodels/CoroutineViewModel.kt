import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineViewModel : ViewModel() {
    val result1 = mutableStateOf("")
    val result2 = mutableStateOf("")
    val result3 = mutableStateOf("")
    val isLoading1 = mutableStateOf(false)
    val isLoading2 = mutableStateOf(false)
    val isLoading3 = mutableStateOf(false)

    fun callToApi1() {
        viewModelScope.launch {
            isLoading1.value = true
            result1.value = withContext(Dispatchers.IO) {
                delay(2000)
                "Resultado 1"
            }
            isLoading1.value = false
        }
    }

    fun callToApi2() {
        viewModelScope.launch {
            isLoading2.value = true
            result2.value = withContext(Dispatchers.IO) {
                delay(4000)
                "Resultado 2"
            }
            isLoading2.value = false
        }
    }

    fun callToApi3() {
        viewModelScope.launch {
            isLoading3.value = true
            result3.value = withContext(Dispatchers.IO) {
                delay(3000)
                "Resultado 3"
            }
            isLoading3.value = false
        }
    }
}
