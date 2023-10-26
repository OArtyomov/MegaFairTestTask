package com.megafair.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "platform_game")
public class PlatformGame {


    @Id
    Long id;

    @Column(name = "platform_id")
    Long platformId;

    @Column(name = "game_id")
    Long gameId;

    Boolean enabled;
}
