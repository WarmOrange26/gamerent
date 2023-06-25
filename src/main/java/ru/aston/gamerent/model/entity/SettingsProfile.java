package ru.aston.gamerent.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "settings_profiles")
public class SettingsProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "settingsProfile", cascade = CascadeType.ALL)
    @ToString.Exclude
    private User user;
    @OneToMany(mappedBy = "settingsProfile", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SettingValue> settings;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SettingsProfile that = (SettingsProfile) o;
        return user.equals(that.user) && settings.equals(that.settings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, settings);
    }
}