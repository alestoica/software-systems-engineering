package repository;

import domain.Librarian;

public interface ILibrarianRepository extends IRepository<String, Librarian> {
    Librarian findOne(String id, String password);
}
