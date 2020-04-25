package org.wit.lists.helpers
/*Author: Paul Shanahan
name: Activity list

 */

import android.content.Context
import android.util.Log
import java.io.*
//this function writes the file to be saved
fun write(context: Context, fileName: String, data: String) {//possibly here
    try {
        val outputStreamWriter = OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE))
        outputStreamWriter.write(data)
        outputStreamWriter.close()
    } catch (e: Exception) {
        Log.e("Error: ", "Cannot read file: " + e.toString());
    }
}
//you can read the file using this function
fun read(context: Context, fileName: String): String {
    var str = ""
    try {
        val inputStream = context.openFileInput(fileName)
        if (inputStream != null) {
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val partialStr = StringBuilder()
            var done = false
            while (!done) {
                val line = bufferedReader.readLine()
                done = (line == null);
                if (line != null) partialStr.append(line);
            }
            inputStream.close()
            str = partialStr.toString()
        }
    } catch (e: FileNotFoundException) {
        Log.e("Error: ", "file not found: " + e.toString());
    } catch (e: IOException) {
        Log.e("Error: ", "cannot read file: " + e.toString());
    }
    return str
}
//check that the file exists
fun exists(context: Context, filename: String): Boolean {
    val file = context.getFileStreamPath(filename)
    return file.exists()
}