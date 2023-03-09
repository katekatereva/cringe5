package dao;

import managers.commandManager.utils.GeneratorID;
import models.LabWork;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class LabWorkFileDAO implements DAO<LabWork> {

    private ArrayDeque<LabWork> labWorkList;

    public LabWorkFileDAO() {
        this.labWorkList = new ArrayDeque<LabWork>();
    }

    public LabWorkFileDAO(ArrayDeque<LabWork> labWorkList) throws CloneNotSupportedException {
        this.labWorkList = new ArrayDeque<LabWork>();
        for (LabWork labWork : labWorkList) {
            this.labWorkList.addLast(labWork.clone());
        }
    }

    @Override
    public void create(LabWork labWork) throws CloneNotSupportedException {

        LabWork createLabWork = labWork.clone();

        createLabWork.setCreationDate(LocalDate.now());
        createLabWork.setId(GeneratorID.newId());

        this.labWorkList.addLast(createLabWork);
    }

    @Override
    public void update(int id, LabWork labWork) throws CloneNotSupportedException {
        for (LabWork labWorkIter : this.labWorkList) {
            if (labWorkIter.getId() == id) {
                labWorkIter.cloneShallow(labWork);
                break;
            }
        }

    }

    @Override
    public void delete(int id) {
        for (LabWork labWorkIter : this.labWorkList) {
            if (labWorkIter.getId() == id) {
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
    }

    @Override
    public LabWork get(int id) throws CloneNotSupportedException {
        for (LabWork labWorkIter : this.labWorkList) {
            if (labWorkIter.getId() == id) {
                return labWorkIter.clone();
            }
        }
        return null;
    }

    @Override
    public ArrayDeque<LabWork> getAll() throws CloneNotSupportedException {
        ArrayDeque<LabWork> labWorkList = new ArrayDeque<>();
        for (LabWork labWork : this.labWorkList) {
            labWorkList.addLast(labWork.clone());
        }
        return labWorkList;
    }
}