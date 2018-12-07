package com.dong.wanandroid.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.dong.wanandroid.data.search.SearchModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SEARCH_MODEL".
*/
public class SearchModelDao extends AbstractDao<SearchModel, Void> {

    public static final String TABLENAME = "SEARCH_MODEL";

    /**
     * Properties of entity SearchModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, int.class, "id", false, "ID");
        public final static Property Link = new Property(1, String.class, "link", false, "LINK");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Order = new Property(3, int.class, "order", false, "ORDER");
        public final static Property Visible = new Property(4, int.class, "visible", false, "VISIBLE");
    }


    public SearchModelDao(DaoConfig config) {
        super(config);
    }
    
    public SearchModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SEARCH_MODEL\" (" + //
                "\"ID\" INTEGER NOT NULL ," + // 0: id
                "\"LINK\" TEXT," + // 1: link
                "\"NAME\" TEXT," + // 2: name
                "\"ORDER\" INTEGER NOT NULL ," + // 3: order
                "\"VISIBLE\" INTEGER NOT NULL );"); // 4: visible
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SEARCH_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SearchModel entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String link = entity.getLink();
        if (link != null) {
            stmt.bindString(2, link);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
        stmt.bindLong(4, entity.getOrder());
        stmt.bindLong(5, entity.getVisible());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SearchModel entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String link = entity.getLink();
        if (link != null) {
            stmt.bindString(2, link);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
        stmt.bindLong(4, entity.getOrder());
        stmt.bindLong(5, entity.getVisible());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public SearchModel readEntity(Cursor cursor, int offset) {
        SearchModel entity = new SearchModel( //
            cursor.getInt(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // link
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.getInt(offset + 3), // order
            cursor.getInt(offset + 4) // visible
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SearchModel entity, int offset) {
        entity.setId(cursor.getInt(offset + 0));
        entity.setLink(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setOrder(cursor.getInt(offset + 3));
        entity.setVisible(cursor.getInt(offset + 4));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(SearchModel entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(SearchModel entity) {
        return null;
    }

    @Override
    public boolean hasKey(SearchModel entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
