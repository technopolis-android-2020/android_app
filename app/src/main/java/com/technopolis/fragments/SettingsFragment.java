package com.technopolis.fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceGroup;
import androidx.preference.SwitchPreference;

import com.technopolis.App;
import com.technopolis.R;
import com.technopolis.database.repositories.AgentRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class SettingsFragment extends PreferenceFragment {
    @Inject
    AgentRepository agentRepository;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        ((App) getActivity().getApplication()).getAppComponent().injectSettingsFragment(this);
        addPreferencesFromResource(R.xml.preference);
        PreferenceGroup preferenceGroup = (PreferenceGroup) findPreference("prefCategory");

        Disposable disposable = (Disposable) agentRepository.getAgents()
                .flatMap(Observable::fromIterable)
                .doOnNext(agent -> {
                    SwitchPreference switchPreference = new SwitchPreference(getActivity());
                    switchPreference.setTitle(agent.name);
                    switchPreference.setDefaultValue(agent.isShown);

                    switchPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                        agentRepository.setIsShown((String) preference.getTitle(), (boolean) newValue);
                        return true;
                    });

                    preferenceGroup.addPreference(switchPreference);
                });
        disposable.dispose();
    }

}
