package com.xiaojerrycat.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    private static final String EXTRA_CRIME_ID = "com.xiaojerrycat.android.criminalintent.crime_id";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    private Button firstButton;
    private Button lastButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = findViewById(R.id.crime_view_pager);
        firstButton = findViewById(R.id.first_crime);
        lastButton = findViewById(R.id.last_crime);

        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager manager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int i) {
                Crime crime = mCrimes.get(i);
                return CrimeFragment.newInstance(crime.getID()); // 将返回的Fragment添加给托管Activity
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        for (int i = 0; i < mCrimes.size(); ++i) {
            if (mCrimes.get(i).getID().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                // 会在滑动的过程中不断调用
                if (mViewPager.getCurrentItem() == 0) { firstButton.setVisibility(View.INVISIBLE); }
                else { firstButton.setVisibility(View.VISIBLE); }
                if (mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() - 1) { lastButton.setVisibility(View.INVISIBLE); }
                else { lastButton.setVisibility(View.VISIBLE); }
            }

            @Override
            public void onPageSelected(int i) {
                // 哪个Pager被选中
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                // 操作屏幕时发生变化
            }
        });
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
            }
        });
        lastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mViewPager.getAdapter().getCount() - 1);
            }
        });
    }

    public static Intent newIntent(Context context, UUID ID) {
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, ID);
        return intent;
    }
}
