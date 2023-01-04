package wize.room.pets

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Pet::class], version = 1, exportSchema = false)
abstract class PetRoomDatabase: RoomDatabase() {

    abstract fun petDao(): PetDao

    //Singleton previene múltiples instancias de apertura de la base de datos al mismo tiempo
    companion object {
        @Volatile
        private var INSTANCE: PetRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope,
        ): PetRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PetRoomDatabase::class.java,
                    "pet_database"
                )
                    .addCallback(PetDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class PetDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.petDao())
                    }
                }
            }
        }
        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(petDao: PetDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            petDao.deleteAll()

            var pet = Pet("Wishbone", "Coker", "male", "9kg")
            petDao.insert(pet)
            pet = Pet("Caliman", "Spaniel", "male", "2kg")
            petDao.insert(pet)
        }
    }
}