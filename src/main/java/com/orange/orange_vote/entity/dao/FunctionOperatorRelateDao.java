package com.orange.orange_vote.entity.dao;

import com.orange.orange_vote.base.repo.BaseRepository;
import com.orange.orange_vote.entity.model.FunctionOperatorRelate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FunctionOperatorRelateDao extends BaseRepository<FunctionOperatorRelate, Integer> {

    @Query("SELECT for FROM FunctionOperatorRelate for WHERE for.function.functionId IN ?1")
    Optional<List<FunctionOperatorRelate>> findAllFunctionOperatorRelatesByFunctionIds(Collection<Integer> functionIds);

    @Query("SELECT for FROM FunctionOperatorRelate for WHERE "
        + "for.status = true AND for.function.status = true AND for.operator.status = true AND "
        + "for.function.functionId IN ?1")
    Optional<List<FunctionOperatorRelate>> findFunctionOperatorRelatesByFunctionIds(Collection<Integer> functionIds);

    @Modifying
    @Query("DELETE FROM FunctionOperatorRelate f WHERE f.functionOperatorRelateId IN ?1")
    void deleteFunctionOperatorRelatesByFunctionOperatorRelateIds(Collection<Integer> functionOperatorRelateIds);

}
