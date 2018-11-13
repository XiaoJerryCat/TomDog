package com.xiaojerrycat.android.criminalintent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xiaojerrycat.android.criminalintent.database.CrimeBaseHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CrimeLab {

    private static CrimeLab sCrimeLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) { sCrimeLab = new CrimeLab(context); }
        return sCrimeLab;
    }

    public List<Crime> getCrimes() {
        return new ArrayList<>();
    }

    public Crime getCrime(UUID id) { return null; }

    public void addCrime(Crime crime) {  }

    public void deleteCrime(UUID Id) {
        Crime crime = getCrime(Id);
        if (crime != null) {  }
    }
}
