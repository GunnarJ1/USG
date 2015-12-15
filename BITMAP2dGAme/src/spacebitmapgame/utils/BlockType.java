package spacebitmapgame.utils;

public enum BlockType {
	Stone("stone"), Air("air"), Grass("grass"), Dirt("dirt");
	public final String location;
	BlockType(String location) {
		this.location = location;
	}
}
