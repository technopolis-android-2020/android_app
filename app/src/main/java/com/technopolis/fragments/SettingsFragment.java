package com.technopolis.fragments;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceGroup;

import android.os.Bundle;
import androidx.preference.SwitchPreference;

import com.technopolis.App;
import com.technopolis.R;
import com.technopolis.database.entity.Agent;
import com.technopolis.database.repositories.AgentRepository;
import javax.inject.Inject;

public class SettingsFragment extends PreferenceFragment {
    @Inject
    AgentRepository agentRepository;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        ((App) getActivity().getApplication()).getAppComponent().injectSettingsFragment(this);
        addPreferencesFromResource(R.xml.preference);
        PreferenceGroup preferenceGroup = (PreferenceGroup) findPreference("prefCategory");

        for (Agent agent : agentRepository.getAgents()) {
            SwitchPreference switchPreference = new SwitchPreference(getActivity());
            switchPreference.setTitle(agent.name);
            switchPreference.setDefaultValue(agent.isShown);

            switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    agentRepository.setIsShown((String) preference.getTitle(), (boolean) newValue);
                    return true;
                }
            });

            preferenceGroup.addPreference(switchPreference);

        }

    }

}
