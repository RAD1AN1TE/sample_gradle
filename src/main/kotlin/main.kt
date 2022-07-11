import java.sql.DriverManager

//database link

data class Doctor(val id: Int, val name: String, val age: Int)

fun main(args: Array<String>) {

    // connection to database
    val jdbcUrl = "jdbc:postgresql://localhost:5432/postgres"
    val connection = DriverManager.getConnection(jdbcUrl, "postgres", "postgres")
    println(connection.isValid(0))


    val query = connection.prepareStatement("SELECT * FROM doc")  // query
    val result = query.executeQuery()                                 // execute
    val query2 = connection.prepareStatement("INSERT INTO doc(doc_id,name,age) VALUES (4, 'phoenix', 55)")
    query2.executeUpdate()
    val doctors = mutableListOf<Doctor>()                             // empty list for holding the results

    while(result.next()){
        // storing the result in values
        val id = result.getInt("doc_id")
        val name = result.getString("name")
        val age = result.getInt("age")

        doctors.add(Doctor(id, name, age))
    }
    println(doctors)

}