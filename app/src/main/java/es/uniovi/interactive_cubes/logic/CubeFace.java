package es.uniovi.interactive_cubes.logic;

public class CubeFace implements Comparable<CubeFace> {

    private String id;

    public CubeFace (String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public int compareTo(CubeFace o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o == this) return true;
        if(!(o instanceof CubeFace)) return false;
        return compareTo((CubeFace)o) == 0;
    }
}
