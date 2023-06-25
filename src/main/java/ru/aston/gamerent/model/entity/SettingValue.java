package ru.aston.gamerent.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "settings_vales")
public class SettingValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    @ToString.Exclude
    private SettingsProfile settingsProfile;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "setting_id")
    private Setting setting;
    @Column(nullable = false)
    private Boolean value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SettingValue that = (SettingValue) o;
        return Objects.equals(settingsProfile, that.settingsProfile) && Objects.equals(setting, that.setting) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(settingsProfile, setting, value);
    }
}
