package sample;

public enum Position {
        Junior("Junior"),
        Middle("Middle"),
        Senior("Senior"),
        Director("Директор");
        private String position;

        Position(String position) {
                this.position=position;
        }

        public String getPosition() {
                return position;
        }
}
