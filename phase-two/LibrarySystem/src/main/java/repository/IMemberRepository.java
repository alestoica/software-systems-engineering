package repository;

import domain.Member;

public interface IMemberRepository extends IRepository<String, Member> {
    Member findOne(String id, String password);
}
