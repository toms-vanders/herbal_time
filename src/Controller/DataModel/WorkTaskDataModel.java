package Controller.DataModel;

import Controller.DataAccessException;
import Controller.WorkTaskCtr;
import Controller.WorkTaskCtrIF;
import Model.WorkTask;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class WorkTaskDataModel extends DefaultTableModel {

    private ArrayList<WorkTask> workTasks = new ArrayList<>();
    private String[] columnNames = {"Date start", "Date end", "Work type", "Hours worked", "Quantity", "Status"};
    private WorkTaskCtrIF workTaskController;
    private String workerCPR;

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
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        WorkTask workTask = workTasks.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return workTask.getDateStart();
            case 1:
                return workTask.getDateEnd();
            case 2:
                return workTask.getWorkType().getDescOfJob();
            case 3:
                return workTask.getHoursWorked();
            case 4:
                return workTask.getQuantity();
            case 5:
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
