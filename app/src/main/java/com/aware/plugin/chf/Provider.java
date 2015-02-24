package com.aware.plugin.chf;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Environment;
import android.provider.BaseColumns;
import android.util.Log;

import com.aware.Aware;
import com.aware.utils.DatabaseHelper;

import java.util.HashMap;

/**
 * Created by denzil on 25/11/14.
 */
public class Provider extends ContentProvider {

    public static final int DATABASE_VERSION = 1;
    public static String AUTHORITY = "com.aware.plugin.upmc.chf.provider";

    private static final int ANSWERS = 1;
    private static final int ANSWERS_ID = 2;

    public static final class CHF_Data implements BaseColumns {
        private CHF_Data(){}

        public static final Uri CONTENT_URI = Uri.parse("content://"+ Provider.AUTHORITY + "/upmc_chf");
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.upmc.chf";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.upmc.chf";

        public static final String _ID = "_id";
        public static final String TIMESTAMP = "timestamp";
        public static final String DEVICE_ID = "device_id";
        public static final String SCORE_SWELLING = "score_swelling";
        public static final String SCORE_SIT_LIE = "score_sit_lie";
        public static final String SCORE_WALKING_CLIMBING = "score_walking_climbing";
        public static final String SCORE_WORKING_HOUSE_YARD = "score_working_house_yard";
        public static final String SCORE_GOING_PLACES_AWAY = "score_going_places_away";
        public static final String SCORE_SLEEPING_WELL = "score_sleeping_well";
        public static final String SCORE_RELATING_FRIENDS_FAMILY = "score_relating_friends_family";
        public static final String SCORE_WORKING_EARN_LIVING = "score_working_earn_living";
        public static final String SCORE_RECREATIONAL_HOBBIES = "score_recreational_hobbies";
        public static final String SCORE_SEXUAL = "score_sexual";
        public static final String SCORE_EATING_LESS = "score_eating_less";
        public static final String SCORE_SHORT_BREATH = "score_short_breath";
        public static final String SCORE_TIRED_LOW_ENERGY = "score_tired_low_energy";
        public static final String SCORE_STAY_HOSPITAL = "score_stay_hospital";
        public static final String SCORE_COST_MEDICAL_CARE = "score_cost_medical_care";
        public static final String SCORE_SIDE_EFFECTS_MEDICATION = "score_side_effects_medication";
        public static final String SCORE_BURDEN_FRIENDS_FAMILY = "score_burden_friends_family";
        public static final String SCORE_LOSS_SELF_CONTROL = "score_loss_self_control";
        public static final String SCORE_WORRY = "score_worry";
        public static final String SCORE_DIFFICULT_CONCENTRATE = "score_difficult_concentrate";
        public static final String SCORE_DEPRESSED = "score_depressed";
    }

    public static String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/AWARE/plugin_upmc_chf.db";
    public static final String[] DATABASE_TABLES = {"upmc_chf"};
    public static final String[] TABLES_FIELDS = {
            CHF_Data._ID + " integer primary key autoincrement," +
            CHF_Data.TIMESTAMP + " real default 0," +
            CHF_Data.DEVICE_ID + " text default ''," +
            CHF_Data.SCORE_SWELLING + " text default ''," +
            CHF_Data.SCORE_SIT_LIE + " text default ''," +
            CHF_Data.SCORE_WALKING_CLIMBING + " text default ''," +
            CHF_Data.SCORE_WORKING_HOUSE_YARD + " text default ''," +
            CHF_Data.SCORE_GOING_PLACES_AWAY + " text default ''," +
            CHF_Data.SCORE_SLEEPING_WELL + " text default ''," +
            CHF_Data.SCORE_RELATING_FRIENDS_FAMILY + " text default ''," +
            CHF_Data.SCORE_WORKING_EARN_LIVING + " text default ''," +
            CHF_Data.SCORE_RECREATIONAL_HOBBIES + " text default ''," +
            CHF_Data.SCORE_SEXUAL + " text default ''," +
            CHF_Data.SCORE_EATING_LESS + " text default ''," +
            CHF_Data.SCORE_SHORT_BREATH + " text default ''," +
            CHF_Data.SCORE_TIRED_LOW_ENERGY + " text default ''," +
            CHF_Data.SCORE_STAY_HOSPITAL + " text default ''," +
            CHF_Data.SCORE_COST_MEDICAL_CARE + " text default ''," +
            CHF_Data.SCORE_SIDE_EFFECTS_MEDICATION + " text default ''," +
            CHF_Data.SCORE_BURDEN_FRIENDS_FAMILY + " text default ''," +
            CHF_Data.SCORE_LOSS_SELF_CONTROL + " text default ''," +
            CHF_Data.SCORE_WORRY + " text default ''," +
            CHF_Data.SCORE_DIFFICULT_CONCENTRATE + " text default ''," +
            CHF_Data.SCORE_DEPRESSED + " text default ''"
    };

    private static UriMatcher sUriMatcher = null;
    private static HashMap<String, String> questionsMap = null;
    private static DatabaseHelper databaseHelper = null;
    private static SQLiteDatabase database = null;

    private boolean initializeDB() {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper( getContext(), DATABASE_NAME, null, DATABASE_VERSION, DATABASE_TABLES, TABLES_FIELDS );
        }
        if( database == null || ! database.isOpen() ) {
            database = databaseHelper.getWritableDatabase();
        }
        return( database != null );
    }

    @Override
    public boolean onCreate() {
        AUTHORITY = getContext().getPackageName() + ".provider";

        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(Provider.AUTHORITY, DATABASE_TABLES[0], ANSWERS);
        sUriMatcher.addURI(Provider.AUTHORITY, DATABASE_TABLES[0] + "/#", ANSWERS_ID);

        questionsMap = new HashMap<>();
        questionsMap.put(CHF_Data._ID, CHF_Data._ID);
        questionsMap.put(CHF_Data.TIMESTAMP, CHF_Data.TIMESTAMP);
        questionsMap.put(CHF_Data.SCORE_SWELLING, CHF_Data.SCORE_SWELLING);
        questionsMap.put(CHF_Data.SCORE_SIT_LIE, CHF_Data.SCORE_SIT_LIE);
        questionsMap.put(CHF_Data.SCORE_WALKING_CLIMBING, CHF_Data.SCORE_WALKING_CLIMBING);
        questionsMap.put(CHF_Data.SCORE_WORKING_HOUSE_YARD, CHF_Data.SCORE_WORKING_HOUSE_YARD);
        questionsMap.put(CHF_Data.SCORE_GOING_PLACES_AWAY, CHF_Data.SCORE_GOING_PLACES_AWAY);
        questionsMap.put(CHF_Data.SCORE_SLEEPING_WELL, CHF_Data.SCORE_SLEEPING_WELL);
        questionsMap.put(CHF_Data.SCORE_RELATING_FRIENDS_FAMILY, CHF_Data.SCORE_RELATING_FRIENDS_FAMILY);
        questionsMap.put(CHF_Data.SCORE_WORKING_EARN_LIVING, CHF_Data.SCORE_WORKING_EARN_LIVING);
        questionsMap.put(CHF_Data.SCORE_RECREATIONAL_HOBBIES, CHF_Data.SCORE_RECREATIONAL_HOBBIES);
        questionsMap.put(CHF_Data.SCORE_SEXUAL, CHF_Data.SCORE_SEXUAL);
        questionsMap.put(CHF_Data.SCORE_EATING_LESS, CHF_Data.SCORE_EATING_LESS);
        questionsMap.put(CHF_Data.SCORE_SHORT_BREATH, CHF_Data.SCORE_SHORT_BREATH);
        questionsMap.put(CHF_Data.SCORE_TIRED_LOW_ENERGY, CHF_Data.SCORE_TIRED_LOW_ENERGY);
        questionsMap.put(CHF_Data.SCORE_STAY_HOSPITAL, CHF_Data.SCORE_STAY_HOSPITAL);
        questionsMap.put(CHF_Data.SCORE_COST_MEDICAL_CARE, CHF_Data.SCORE_COST_MEDICAL_CARE);
        questionsMap.put(CHF_Data.SCORE_SIDE_EFFECTS_MEDICATION, CHF_Data.SCORE_SIDE_EFFECTS_MEDICATION);
        questionsMap.put(CHF_Data.SCORE_BURDEN_FRIENDS_FAMILY, CHF_Data.SCORE_BURDEN_FRIENDS_FAMILY);
        questionsMap.put(CHF_Data.SCORE_LOSS_SELF_CONTROL, CHF_Data.SCORE_LOSS_SELF_CONTROL);
        questionsMap.put(CHF_Data.SCORE_WORRY, CHF_Data.SCORE_WORRY);
        questionsMap.put(CHF_Data.SCORE_DIFFICULT_CONCENTRATE, CHF_Data.SCORE_DIFFICULT_CONCENTRATE);
        questionsMap.put(CHF_Data.SCORE_DEPRESSED, CHF_Data.SCORE_DEPRESSED);

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if( ! initializeDB() ) {
            Log.w(AUTHORITY,"Database unavailable...");
            return null;
        }

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        switch (sUriMatcher.match(uri)) {
            case ANSWERS:
                qb.setTables(DATABASE_TABLES[0]);
                qb.setProjectionMap(questionsMap);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        try {
            Cursor c = qb.query(database, projection, selection, selectionArgs,
                    null, null, sortOrder);
            c.setNotificationUri(getContext().getContentResolver(), uri);
            return c;
        } catch (IllegalStateException e) {
            if (Aware.DEBUG)
                Log.e(Aware.TAG, e.getMessage());

            return null;
        }
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case ANSWERS:
                return CHF_Data.CONTENT_TYPE;
            case ANSWERS_ID:
                return CHF_Data.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
        if( ! initializeDB() ) {
            Log.w(AUTHORITY,"Database unavailable...");
            return null;
        }

        ContentValues values = (initialValues != null) ? new ContentValues(
                initialValues) : new ContentValues();

        switch (sUriMatcher.match(uri)) {
            case ANSWERS:
                database.beginTransaction();
                long quest_id = database.insertWithOnConflict(DATABASE_TABLES[0],
                        CHF_Data.DEVICE_ID, values, SQLiteDatabase.CONFLICT_IGNORE);
                database.setTransactionSuccessful();
                database.endTransaction();
                if (quest_id > 0) {
                    Uri questUri = ContentUris.withAppendedId(CHF_Data.CONTENT_URI,
                            quest_id);
                    getContext().getContentResolver().notifyChange(questUri, null);
                    return questUri;
                }
                throw new SQLException("Failed to insert row into " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if( ! initializeDB() ) {
            Log.w(AUTHORITY, "Database unavailable...");
            return 0;
        }

        int count = 0;
        switch (sUriMatcher.match(uri)) {
            case ANSWERS:
                database.beginTransaction();
                count = database.delete(DATABASE_TABLES[0], selection,
                        selectionArgs);
                database.setTransactionSuccessful();
                database.endTransaction();
                break;
            default:

                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if( ! initializeDB() ) {
            Log.w(AUTHORITY,"Database unavailable...");
            return 0;
        }

        int count = 0;
        switch (sUriMatcher.match(uri)) {
            case ANSWERS:
                database.beginTransaction();
                count = database.update(DATABASE_TABLES[0], values, selection,
                        selectionArgs);
                database.setTransactionSuccessful();
                database.endTransaction();
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
