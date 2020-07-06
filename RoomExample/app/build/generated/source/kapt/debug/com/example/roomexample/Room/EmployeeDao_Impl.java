package com.example.roomexample.Room;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class EmployeeDao_Impl implements EmployeeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Employee> __insertionAdapterOfEmployee;

  private final EntityDeletionOrUpdateAdapter<Employee> __deletionAdapterOfEmployee;

  public EmployeeDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEmployee = new EntityInsertionAdapter<Employee>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Employee` (`eId`,`firstName`,`lastName`,`city`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Employee value) {
        stmt.bindLong(1, value.getEId());
        if (value.getFirstName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFirstName());
        }
        if (value.getLastName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLastName());
        }
        if (value.getCity() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCity());
        }
      }
    };
    this.__deletionAdapterOfEmployee = new EntityDeletionOrUpdateAdapter<Employee>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Employee` WHERE `eId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Employee value) {
        stmt.bindLong(1, value.getEId());
      }
    };
  }

  @Override
  public void insertAll(final Employee... employee) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEmployee.insert(employee);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Employee employee) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfEmployee.handle(employee);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Employee>> listAll() {
    final String _sql = "SELECT * FROM Employee";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Employee"}, false, new Callable<List<Employee>>() {
      @Override
      public List<Employee> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfEId = CursorUtil.getColumnIndexOrThrow(_cursor, "eId");
          final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "firstName");
          final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "lastName");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final List<Employee> _result = new ArrayList<Employee>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Employee _item;
            final int _tmpEId;
            _tmpEId = _cursor.getInt(_cursorIndexOfEId);
            final String _tmpFirstName;
            _tmpFirstName = _cursor.getString(_cursorIndexOfFirstName);
            final String _tmpLastName;
            _tmpLastName = _cursor.getString(_cursorIndexOfLastName);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            _item = new Employee(_tmpEId,_tmpFirstName,_tmpLastName,_tmpCity);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Employee getByName(final String firstName) {
    final String _sql = "SELECT * FROM EMPLOYEE WHERE firstName like ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (firstName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, firstName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfEId = CursorUtil.getColumnIndexOrThrow(_cursor, "eId");
      final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "firstName");
      final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "lastName");
      final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
      final Employee _result;
      if(_cursor.moveToFirst()) {
        final int _tmpEId;
        _tmpEId = _cursor.getInt(_cursorIndexOfEId);
        final String _tmpFirstName;
        _tmpFirstName = _cursor.getString(_cursorIndexOfFirstName);
        final String _tmpLastName;
        _tmpLastName = _cursor.getString(_cursorIndexOfLastName);
        final String _tmpCity;
        _tmpCity = _cursor.getString(_cursorIndexOfCity);
        _result = new Employee(_tmpEId,_tmpFirstName,_tmpLastName,_tmpCity);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
