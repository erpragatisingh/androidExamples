package pragati.singh.iimt;

public class status {
	
	 private long id;
    private String createdAt,suser;

    public long getId() {
          return id;
    }

    public void setId(long id) {
          this.id = id;
    }

    public String getCreatedAt() {
          return createdAt;
    }

    public void setComment(String createdAt) {
          this.createdAt = createdAt;
    }

    public String getUser() {
        return suser;
  }

  public void getUser(String suser) {
        this.suser = suser;
  }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
          return suser;
    }

}
