/** Represents a user in a social network. A user is characterized by a name,
 *  a list of user names that s/he follows, and the list's size. */
 public class User {

    // Maximum number of users that a user can follow
    static int maxfCount = 10;

    private String name;       // name of this user
    private String[] follows;  // array of user names that this user follows
    private int fCount;        // actual number of followees (must be <= maxfCount)

    /** Creates a user with an empty list of followees. */
    public User(String name) {
        this.name = name;
        follows = new String[maxfCount]; // fixed-size array for storing followees
        fCount = 0;                      // initial number of followees
    }

    /** Creates a user with some followees. The only purpose of this constructor is 
     *  to allow testing the toString and follows methods, before implementing other methods. */
    public User(String name, boolean gettingStarted) {
        this(name);
        follows[0] = "Foo";
        follows[1] = "Bar";
        follows[2] = "Baz";
        fCount = 3;
    }

    /** Returns the name of this user. */
    public String getName() {
        return name;
    }

    /** Returns the follows array. */
    public String[] getfFollows() {
        return follows;
    }

    /** Returns the number of users that this user follows. */
    public int getfCount() {
        return fCount;
    }

    /** If this user follows the given name, returns true; otherwise returns false. */
    public boolean follows(String name) {
        for (int i = 0; i < this.follows.length; i++) {
            if (this.follows[i]==null){
                return false;
            }
            if (this.follows[i].equals(name)){
                //if the name is found in the array - return true 
                return true; 
            }
        }
        return false;
    }
    /** Makes this user follow the given name. If successful, returns true. 
     *  If this user already follows the given name, or if the follows list is full, does nothing and returns false; */
    public boolean addFollowee(String name) {
        //if the number of the null follows is 10 - the list is full
        //if (this.follows(null)==false){return false;}

        //if the user is already in the array
        if (this.follows(name)==true){
            return false;
        }
        else {
            for (int i = 0; i < this.follows.length; i++) {
                if (this.follows[i]==null){
                    this.follows[i]=name;
                    this.fCount++;
                    return true; 
                }
            }
        }
        return false;
    }

    /** Removes the given name from the follows list of this user. If successful, returns true.
     *  If the name is not in the list, does nothing and returns false. */
    public boolean removeFollowee(String name) {
        int index = 0;
        //If the name is not in the list
        if (this.follows(name)==false){
            return false;
        }
        else {
            for (int i = 0; i < this.follows.length; i++) {
                if (this.follows[i].equals(name)){
                    index = i;
                    //this.follows[i]=null;
                    break;
                }
            }
            for (int i = index; i < (this.follows.length-index) ; i++) {
                if (this.follows[i]!=null){
                    this.follows[i]=this.follows[i+1];
                } 
            }
            this.fCount--;
        }
        return true;
    }

    /** Counts the number of users that both this user and the other user follow.
    /*  Notice: This is the size of the intersection of the two follows lists. */
    public int countMutual(User other) {
        int counter = 0;
        for (int i = 0; i < other.follows.length; i++) {
            //each iteration will check if a follow of other is in this 
            //also checks that counter is not increased if the compared follow is null
            if ((this.follows(other.follows[i])==true)&&(other.follows[i]!=null)) {
                counter++;
            }
        }
        return counter;
    }

    /** Checks is this user is a friend of the other user.
     *  (if two users follow each other, they are said to be "friends.") */
    public boolean isFriendOf(User other) {
        for (int i = 0; i < 10; i++) {
            if ((this.follows(other.follows[i]))&&(other.follows(this.follows[i]))){
                return true;
            }
        //return false; was here 
        }
        return false;
    }
    /** Returns this user's name, and the names that s/he follows. */
    public String toString() {
        String ans = name + " -> ";
        for (int i = 0; i < fCount; i++) {
            ans = ans + follows[i] + " ";
        }
        return ans;
    }
}
