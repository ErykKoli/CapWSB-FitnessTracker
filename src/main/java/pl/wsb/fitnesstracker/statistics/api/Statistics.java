package pl.wsb.fitnesstracker.statistics.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

@Entity
@Table(name = "Statistics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "total_trainings", nullable = false)
    private int totalTrainings;

    @Column(name = "total_distance", nullable = false)
    private double totalDistance;

    @Column(name = "total_calories_burned", nullable = false)
    private int totalCaloriesBurned;

    public Statistics(User user, int totalTrainings, double totalDistance, int totalCaloriesBurned) {
        if (user == null) throw new IllegalArgumentException("user cannot be null");
        if (totalTrainings < 0 || totalDistance < 0 || totalCaloriesBurned < 0) {
            throw new IllegalArgumentException("statistics values must be >= 0");
        }
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }

    public void addTraining(double distance, int calories) {
        if (distance < 0 || calories < 0) throw new IllegalArgumentException("values must be >= 0");
        this.totalTrainings++;
        this.totalDistance += distance;
        this.totalCaloriesBurned += calories;
    }
}