package test;

import lombok.Data;
import lombok.Setter;

public class OrderEvent {
        private String id;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }
}
