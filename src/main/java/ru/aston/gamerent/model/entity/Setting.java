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
@Table(name = "settings")
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "setting_name", unique = true, nullable = false, columnDefinition = "CHARACTER VARYING(30)")
    private String settingName;
    @OneToMany(mappedBy = "setting")
    @ToString.Exclude
    private List<SettingValue> settingsProfiles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Setting setting = (Setting) o;
        return settingName.equals(setting.settingName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(settingName);
    }
}