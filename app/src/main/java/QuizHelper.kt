// QuizHelper.kt
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.random.Random

data class Question(
    val question: String,
    val answers: List<String>,
    val correctAnswer: Int
)

object QuizHelper {

    // assets から JSON を読み込んでランダムに 1 問取得
    fun getRandomQuestion(context: Context, filename: String = "data.json"): Question? {
        return try {
            val inputStream = context.assets.open(filename)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val jsonString = reader.readText()
            reader.close()

            // JSON をリストに変換
            val listType = object : TypeToken<List<Question>>() {}.type
            val questions: List<Question> = Gson().fromJson(jsonString, listType)

            // ランダムに 1 問選択
            if (questions.isNotEmpty()) questions[Random.nextInt(questions.size)] else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
