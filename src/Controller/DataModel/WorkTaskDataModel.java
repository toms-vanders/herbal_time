package Controller.DataModel;

import Controller.DataAccessException;
import Controller.WorkTaskCtr;
import Controller.WorkTaskCtrIF;
import Model.WorkTask;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class WorkTaskDataModel extends DefaultTableModel {

    private ArrayList<WorkTask> workTasks = new ArrayList<>();
    private final String[] columnNames = {"Date", "Work type", "Hours worked", "Quantity", "Status"};
    private final WorkTaskCtrIF workTaskController;
    private final String workerCPR;

    public WorkTaskDataModel(ArrayList<WorkTask> workTasks,String workerCPR) throws DataAccessException {
        this.workerCPR = workerCPR;
        workTaskController = new WorkTaskCtr();
        this.workTasks.addAll(workTasks);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        if (workTasks.isEmpty()) {
            return Object.class;
        }

        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public int getRowCount() {
        return workTasks == null ? 0 : workTasks.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        WorkTask workTask = workTasks.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return workTask.getDateStart();
            case 1:
                return workTask.getWorkType().getDescOfJob();
            case 2:
                return workTask.getHoursWorked();
            case 3:
                return workTask.getQuantity();
            case 4:
                return workTask.getStatus();
            default:
                return null;
        }
    }

    public void updateData() throws DataAccessException {
        setRowCount(0);
        this.workTasks = new ArrayList<>(workTaskController.findAllWorkTasksOfWorker(true,workerCPR));
        fireTableDataChanged();
    }
}
