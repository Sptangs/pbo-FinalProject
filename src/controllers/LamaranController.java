package controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import java.util.ArrayList;
import java.util.List;

import models.Lamaran;
import models.LowonganPekerjaan;
import models.Pekerja;
import models.StatusLamaran;

public class LamaranController {


    private final List<Lamaran> applicationList;

    private String message;


    public LamaranController() {

        applicationList = new ArrayList<>();

        message = "";
    }


    public boolean addApplication(
            LowonganPekerjaan job,
            Pekerja worker,
            String cvPath,
            String coverLetter
    ) {

        try {

            if (job == null) {

                throw new IllegalArgumentException(
                        "Job posting cannot be empty");
            }

            if (worker == null) {

                throw new IllegalArgumentException(
                        "Worker cannot be empty");
            }

            if (cvPath == null
                    || cvPath.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "CV must be filled");
            }

            if (coverLetter == null
                    || coverLetter.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Cover letter must be filled");
            }

            for (Lamaran application : applicationList) {

                boolean sameWorker =
                        application.getPekerja()
                                .equals(worker);

                boolean sameJob =
                        application.getLowongan()
                                .equals(job);

                if (sameWorker
                        && sameJob) {

                    throw new IllegalArgumentException(
                            "Worker has already applied for this job");
                }
            }

            // ================= UPLOAD CV =================

            File selectedFile =
                    new File(cvPath);

            if (!selectedFile.exists()) {

                throw new IllegalArgumentException(
                        "CV file not found");
            }

            String fileName =
                    System.currentTimeMillis()
                    + "_"
                    + selectedFile.getName();

            File destinationFolder =
                    new File("src/assets/cv");

            if (!destinationFolder.exists()) {

                destinationFolder.mkdirs();
            }

            File destinationFile =
                    new File(
                            destinationFolder,
                            fileName
                    );

            Files.copy(
                    selectedFile.toPath(),
                    destinationFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            String savedCvPath =
                    destinationFile.getPath();

            // ================= CREATE APPLICATION =================

            Lamaran application =
                    new Lamaran(
                            job,
                            worker,
                            savedCvPath,
                            coverLetter
                    );

            applicationList.add(application);

            message =
                    "Application successfully added";

            return true;

        } catch (IllegalArgumentException e) {

            message = e.getMessage();

            return false;

        } catch (IOException e) {

            message =
                    "Failed to upload CV: "
                    + e.getMessage();

            return false;
        }
    }


    public List<Lamaran> getAllApplications() {

        return new ArrayList<>(applicationList);
    }

    public Lamaran getApplicationById(
            int id
    ) {

        for (Lamaran application : applicationList) {

            if (application.getId() == id) {

                return application;
            }
        }

        return null;
    }


    public List<Lamaran> getApplicationByWorker(
            Pekerja worker
    ) {

        List<Lamaran> result =
                new ArrayList<>();

        for (Lamaran application : applicationList) {

            if (application.getPekerja()
                    .equals(worker)) {

                result.add(application);
            }
        }

        return result;
    }

    public List<Lamaran> getApplicationByJob(
            LowonganPekerjaan job
    ) {

        List<Lamaran> result =
                new ArrayList<>();

        for (Lamaran application : applicationList) {

            if (application.getLowongan()
                    .equals(job)) {

                result.add(application);
            }
        }

        return result;
    }

    public List<Lamaran> getApplicationByStatus(
            StatusLamaran status
    ) {

        List<Lamaran> result =
                new ArrayList<>();

        for (Lamaran application : applicationList) {

            if (application.getStatus()
                    == status) {

                result.add(application);
            }
        }

        return result;
    }

    public List<Lamaran> searchByWorkerName(
            String keyword
    ) {

        List<Lamaran> result =
                new ArrayList<>();

        if (keyword == null) {

            return result;
        }

        for (Lamaran application : applicationList) {

            String name =
                    application.getPekerja()
                            .getNama()
                            .toLowerCase();

            if (name.contains(
                    keyword.toLowerCase())) {

                result.add(application);
            }
        }

        return result;
    }

    public List<Lamaran> searchByJobTitle(
            String keyword
    ) {

        List<Lamaran> result =
                new ArrayList<>();

        if (keyword == null) {

            return result;
        }

        for (Lamaran application : applicationList) {

            String title =
                    application.getLowongan()
                            .getJudul()
                            .toLowerCase();

            if (title.contains(
                    keyword.toLowerCase())) {

                result.add(application);
            }
        }

        return result;
    }


    public boolean processApplication(
            Lamaran application
    ) {

        try {

            if (application == null) {

                throw new IllegalArgumentException(
                        "Application not found");
            }

            if (application.getStatus()
                    != StatusLamaran.DIAJUKAN) {

                throw new IllegalArgumentException(
                        "Only submitted applications can be reviewed");
            }

            application.processApplication();

            message =
                    "Application successfully processed";

            return true;

        } catch (IllegalArgumentException e) {

            message = e.getMessage();

            return false;
        }
    }

    public boolean acceptApplication(
            Lamaran application
    ) {

        try {

            if (application == null) {

                throw new IllegalArgumentException(
                        "Application not found");
            }

            if (application.getStatus()
                    != StatusLamaran.DIREVIEW) {

                throw new IllegalArgumentException(
                        "Application must be reviewed first");
            }

            application.acceptApplication();

            message =
                    "Application successfully accepted";

            return true;

        } catch (IllegalArgumentException e) {

            message = e.getMessage();

            return false;
        }
    }

    public boolean rejectApplication(
            Lamaran application,
            String reason
    ) {

        try {

            if (application == null) {

                throw new IllegalArgumentException(
                        "Application not found");
            }

            if (application.getStatus()
                    != StatusLamaran.DIREVIEW) {

                throw new IllegalArgumentException(
                        "Application must be reviewed first");
            }

            if (reason == null
                    || reason.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Rejection reason must be filled");
            }

            application.rejectApplication(reason);

            message =
                    "Application successfully rejected";

            return true;

        } catch (IllegalArgumentException e) {

            message = e.getMessage();

            return false;
        }
    }


    public boolean updateApplication(
            Lamaran application,
            String cvPath,
            String coverLetter
    ) {

        try {

            if (application == null) {

                throw new IllegalArgumentException(
                        "Application not found");
            }

            if (!application.canBeEdited()) {

                throw new IllegalArgumentException(
                        "Application cannot be edited");
            }

            if (cvPath == null
                    || cvPath.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "CV must be filled");
            }

            if (coverLetter == null
                    || coverLetter.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Cover letter must be filled");
            }

            application.setCvPath(
                    cvPath);

            application.setCoverLetter(
                    coverLetter);

            message =
                    "Application successfully updated";

            return true;

        } catch (IllegalArgumentException e) {

            message = e.getMessage();

            return false;
        }
    }


    public boolean deleteApplication(
            Lamaran application
    ) {

        try {

            if (application == null) {

                throw new IllegalArgumentException(
                        "Application not found");
            }

            boolean success =
                    applicationList.remove(
                            application);

            if (!success) {

                throw new IllegalArgumentException(
                        "Application deletion failed");
            }

            message =
                    "Application successfully deleted";

            return true;

        } catch (IllegalArgumentException e) {

            message = e.getMessage();

            return false;
        }
    }


    public int getTotalApplications() {

        return applicationList.size();
    }

    public boolean isEmpty() {

        return applicationList.isEmpty();
    }

    public String getMessage() {

        return message;
    }

    public void clearMessage() {

        message = "";
    }
}