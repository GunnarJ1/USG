package spacebitmapgame.utils;

public enum BlockType {
	Stone("stone", true), Air("air", false), Grass("grass", true), Dirt("dirt", true);
	public final String location;
	public final boolean solid;
	BlockType(String location, boolean solid) {
		this.location = location;
		this.solid = solid;
	}
}
