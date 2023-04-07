package at.technikumwien.events;

import at.technikumwien.entities.Author;

import java.util.ArrayList;

public class AuthorList<Object> extends ArrayList<Author> {
    public Author getManagedAuthor(Author requestedAuthor) {
        for (Author author : this){
            if (requestedAuthor.getId() == author.getId()){
                return author;
            }
        }
        return null;
    }

    public boolean containsAuthor(Author requestedAuthor) {
        for (Author author : this){
            if (requestedAuthor.getId() == author.getId()){
                return true;
            }
        }
        return false;
    }
}
