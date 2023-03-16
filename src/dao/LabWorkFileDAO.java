package dao;

import managers.commandManager.utils.GeneratorID;
import models.LabWork;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LabWorkFileDAO implements DAO<LabWork> {

    private final ArrayDeque<LabWork> labWorkList;
//    private TreeMap<Integer, Integer> hashLabWorkList = new TreeMap<>();

    public LabWorkFileDAO() {
        this.labWorkList = new ArrayDeque<>();
    }

    public LabWorkFileDAO(ArrayDeque<LabWork> labWorkList){
        this.labWorkList = new ArrayDeque<LabWork>();
        for (LabWork labWork : labWorkList) {
//            hashLabWorkList.put(labWork.getId(), labWork.getName().length());
            this.labWorkList.addLast(new LabWork(labWork));
        }

//        hashLabWorkList = (TreeMap<Integer, Integer>) ComparatorLabWorkTreeMap.sortByValues(hashLabWorkList);
    }


//    public Map<Integer, Integer> getHashList() {
//        return hashLabWorkList;
//    }


    @Override
    public void create(LabWork labWork) {

        LabWork createLabWork = new LabWork(labWork);
        createLabWork.setCreationDate(LocalDate.now());
        createLabWork.setId(GeneratorID.newId());

//        hashLabWorkList.put(createLabWork.getId(), createLabWork.getName().length());
        this.labWorkList.addLast(createLabWork);
//        hashLabWorkList = ComparatorLabWorkTreeMap.sortByValues(hashLabWorkList);

    }

    @Override
    public void update(int id, LabWork labWork) {
        for (LabWork labWorkIter : this.labWorkList) {
            if (labWorkIter.getId() == id) {

                labWorkIter.setName(labWork.getName());
                labWorkIter.setAuthor(labWork.getAuthor());
                labWorkIter.setCoordinates(labWork.getCoordinates());
                labWorkIter.setDifficulty(labWork.getDifficulty());
                labWorkIter.setMinimalPoint(labWork.getMinimalPoint());
                labWorkIter.setMaximumPoint(labWork.getMaximumPoint());

//                hashLabWorkList.put(labWorkIter.getId(), labWorkIter.getName().length());
//                hashLabWorkList = (TreeMap<Integer, Integer>) ComparatorLabWorkTreeMap.sortByValues(hashLabWorkList);


                break;
            }
        }

    }

    @Override
    public void delete(int id) {
        for (LabWork labWorkIter : this.labWorkList) {
            if (labWorkIter.getId() == id) {
//                hashLabWorkList.remove(labWorkIter.getId());
//                hashLabWorkList = (TreeMap<Integer, Integer>) ComparatorLabWorkTreeMap.sortByValues(hashLabWorkList);

                this.labWorkList.remove(labWorkIter);

            }
        }
    }

    @Override
    public void delete(LabWork labWork) {
        delete(labWork.getId());
    }

    @Override
    public void clear() {
        this.labWorkList.clear();
//        hashLabWorkList.clear();
        GeneratorID.setId(1);
    }

    @Override
    public LabWork get(int id) {
        for (LabWork labWorkIter : this.labWorkList) {
            if (labWorkIter.getId() == id) {
                return new LabWork(labWorkIter);
            }
        }
        return null;
    }

//    @Override
//    public LabWork getMin() {
//        Map.Entry<Integer, Integer> entry = hashLabWorkList.entrySet().iterator().next();
//        return get(entry.getValue());
//    }

    @Override
    public LabWork getFirst() {
        return new LabWork(this.labWorkList.getFirst());
    }

    @Override
    public ArrayDeque<LabWork> getAll() {
        ArrayDeque<LabWork> labWorkList = new ArrayDeque<>();
        for (LabWork labWork : this.labWorkList) {
            labWorkList.addLast(new LabWork(labWork));
        }
        return labWorkList;
    }


}