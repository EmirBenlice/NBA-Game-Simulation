package Player;

public abstract class Player {
    protected String name;
    protected String position;
    protected double pts;
    protected double trb;
    protected double ast;
    protected double blk;
    protected double stl;

    public Player(String name, double pts, double trb, double ast, double blk, double stl) {
        this.name = name;
        this.position = this.getClass().getSimpleName();
        this.pts = pts;
        this.trb = trb;
        this.ast = ast;
        this.blk = blk;
        this.stl = stl;
    }

    public abstract int calculateScore();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public double getPts() {
		return pts;
	}

	public void setPts(int pts) {
		this.pts = pts;
	}

	public double getTrb() {
		return trb;
	}

	public void setTrb(int trb) {
		this.trb = trb;
	}

	public double getAst() {
		return ast;
	}

	public void setAst(int ast) {
		this.ast = ast;
	}

	public double getBlk() {
		return blk;
	}

	public void setBlk(int blk) {
		this.blk = blk;
	}

	public double getStl() {
		return stl;
	}

	public void setStl(int stl) {
		this.stl = stl;
	}
	public interface GameUpdateListener {
	    void onGameCompleted();
	}

    
}
