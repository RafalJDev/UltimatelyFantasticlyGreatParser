package newash.controller;

import newash.actionscript.stage.stages.*;
import newash.controller.stages.StagesController;
import newash.io.readers.CodeReader;
import newash.io.readers.current.CodeLineEntity;
import newash.io.writers.CodeWriter;
import newash.io.writers.ImportWriter;
import newash.parser.finder.StagesFinder;

/**
 * Created by Jaszczynski.Rafal on 02.03.2018.
 */
public class Controller {

  private CodeReader codeReader;
  private CodeWriter codeWriter;

  private ImportWriter importWriter;

  private StagesFinder stagesFinder;

  CodeLineEntity codeLineEntity = CodeLineEntity.getInstance();

  UiDesignStage uiDesignStage;
  ImportStage importStage = ImportStage.getInstance(); //TODO the fucken machen
  ActionScriptStage actionScriptStage;
  FxDeclarationStage fxDeclarationStage;
  ComponentsStage componentsStage;

  StagesController stagesController;

  public Controller() {
    uiDesignStage = UiDesignStage.getInstance();
    importStage = ImportStage.getInstance();
    actionScriptStage = ActionScriptStage.getInstance();
    fxDeclarationStage = FxDeclarationStage.getInstance();
    componentsStage = ComponentsStage.getInstance();

    codeReader = new CodeReader();
    codeWriter = new CodeWriter();



    stagesFinder = new StagesFinder();
    stagesController = new StagesController();
  }

  public void launchIt() {

    codeReader.openFileAndGetBufferedReader();
    stagesFinder.findStages();
    codeReader.closeBufferedReader();

    codeReader.openFileAndGetBufferedReader();
    stagesController.parseAllStages();
    codeReader.closeBufferedReader();

    saveOutputCode();

    printData();

  }

  public void saveOutputCode() {
    codeWriter.saveFile();
  }

  public void printData() {
    System.out.println(codeLineEntity.toString());

    System.out.println(uiDesignStage.toString());
    System.out.println();

    System.out.println(importStage.toString());
    System.out.println(actionScriptStage.toString());
    System.out.println(fxDeclarationStage.toString());
    System.out.println(componentsStage.toString());
  }
}