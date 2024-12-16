package Flame._2.BloodCare.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class BloodAvailability {

    @EmbeddedId
    private BloodAvailabilityId id;  // Composite primary key

    @Column(name = "units_available")
    private int unitsAvailable;

    // Getters and Setters
    public BloodAvailabilityId getId() {
        return id;
    }

    public void setId(BloodAvailabilityId id) {
        this.id = id;
    }

    public int getUnitsAvailable() {
        return unitsAvailable;
    }

    public void setUnitsAvailable(int unitsAvailable) {
        this.unitsAvailable = unitsAvailable;
    }

    @Embeddable
    public static class BloodAvailabilityId implements Serializable {

        @Column(name = "blood_group")
        private String bloodGroup;

        @Column(name = "component_type")
        private String componentType;

        // Default constructor
        public BloodAvailabilityId() {}

        public BloodAvailabilityId(String bloodGroup, String componentType) {
            this.bloodGroup = bloodGroup;
            this.componentType = componentType;
        }

        // Getters and Setters
        public String getBloodGroup() {
            return bloodGroup;
        }

        public void setBloodGroup(String bloodGroup) {
            this.bloodGroup = bloodGroup;
        }

        public String getComponentType() {
            return componentType;
        }

        public void setComponentType(String componentType) {
            this.componentType = componentType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BloodAvailabilityId that = (BloodAvailabilityId) o;
            return Objects.equals(bloodGroup, that.bloodGroup) &&
                   Objects.equals(componentType, that.componentType);
        }

        @Override
        public int hashCode() {
            return Objects.hash(bloodGroup, componentType);
        }
    }
}