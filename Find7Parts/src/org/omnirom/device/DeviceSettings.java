/*
* Copyright (C) 2013 The OmniROM Project
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*
*/
package org.omnirom.device;

import android.os.Bundle;
import android.preference.SwitchPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.TwoStatePreference;
import android.provider.Settings;

public class DeviceSettings extends PreferenceActivity implements OnPreferenceChangeListener {

    public static final String KEY_DOUBLE_TAP_SWITCH = "double_tap";
    public static final String KEY_CAMERA_SWITCH = "camera";
    public static final String KEY_TORCH_SWITCH = "torch";


    private static final String KEY_PROXIMITY_WAKE = "proximity_on_wake";
    private static final String CATEGORY_ADVANCED = "advanced_display_prefs";

    private static final String KEY_HAPTIC_FEEDBACK = "touchscreen_haptic_feedback";

/* Commented out until reimplemented on F7
    public static final String KEY_MUSIC_SWITCH = "music";

    public static final String KEY_VIBSTRENGTH = "vib_strength";
*/
    private TwoStatePreference mDoubleTapSwitch;
    private TwoStatePreference mTorchSwitch;
    private TwoStatePreference mCameraSwitch;
    private SwitchPreference mHapticSwitch;

/*  private TwoStatePreference mMusicSwitch;
*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main);

        mDoubleTapSwitch = (TwoStatePreference) findPreference(KEY_DOUBLE_TAP_SWITCH);
        mDoubleTapSwitch.setEnabled(DoubleTapSwitch.isSupported());
        mDoubleTapSwitch.setChecked(DoubleTapSwitch.isEnabled(this));
        mDoubleTapSwitch.setOnPreferenceChangeListener(new DoubleTapSwitch());

        mTorchSwitch = (TwoStatePreference) findPreference(KEY_TORCH_SWITCH);
        mTorchSwitch.setEnabled(TorchGestureSwitch.isSupported());
        mTorchSwitch.setChecked(TorchGestureSwitch.isEnabled(this));
        mTorchSwitch.setOnPreferenceChangeListener(new TorchGestureSwitch());

        mCameraSwitch = (TwoStatePreference) findPreference(KEY_CAMERA_SWITCH);
        mCameraSwitch.setEnabled(CameraGestureSwitch.isSupported());
        mCameraSwitch.setChecked(CameraGestureSwitch.isEnabled(this));
        mCameraSwitch.setOnPreferenceChangeListener(new CameraGestureSwitch());

        mHapticSwitch = (SwitchPreference) findPreference(KEY_HAPTIC_FEEDBACK);
        mHapticSwitch.setOnPreferenceChangeListener(this);

        /*mMusicSwitch = (TwoStatePreference) findPreference(KEY_MUSIC_SWITCH);
        mMusicSwitch.setEnabled(MusicGestureSwitch.isSupported());
        mMusicSwitch.setChecked(MusicGestureSwitch.isEnabled(this));
        mMusicSwitch.setOnPreferenceChangeListener(new MusicGestureSwitch());

        mSuspendFreqCap = (SuspendFreqCap) findPreference(KEY_SUSPEND_CAP_FREQ);
        mSuspendFreqCap.setEnabled(SuspendFreqCap.isSupported());
        mSuspendFreqCap.setValue(SuspendFreqCap.getValue(this));
        mSuspendFreqCap.setOnPreferenceChangeListener(mSuspendFreqCap);

        mSuspendCoreCap = (SuspendCoreCap) findPreference(KEY_SUSPEND_CAP_CORE);
        mSuspendCoreCap.setEnabled(SuspendCoreCap.isSupported());
        mSuspendCoreCap.setValue(SuspendCoreCap.getValue(this));
        mSuspendCoreCap.setOnPreferenceChangeListener(mSuspendCoreCap);
*/
        PreferenceCategory advancedPrefs = (PreferenceCategory) findPreference(CATEGORY_ADVANCED);
        boolean proximityCheckOnWait = getResources().getBoolean(
                com.android.internal.R.bool.config_proximityCheckOnWake);
        if (!proximityCheckOnWait) {
            advancedPrefs.removePreference(findPreference(KEY_PROXIMITY_WAKE));
            Settings.System.putInt(getContentResolver(), Settings.System.PROXIMITY_ON_WAKE, 1);
        }

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final String key = preference.getKey();
        if (KEY_HAPTIC_FEEDBACK.equals(key)) {
            final boolean value = (Boolean) newValue;
            return true;
        }
	return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
