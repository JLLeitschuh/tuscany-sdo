/**
 *
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.tuscany.samples.sdo.specExampleSection;

import java.io.InputStream;
import java.util.List;

import org.apache.tuscany.samples.sdo.SampleBase;
import org.apache.tuscany.samples.sdo.SdoSampleConstants;

import commonj.sdo.DataObject;
import commonj.sdo.helper.HelperContext;

/**
 * Demonstrates accessing the properties of a DataObject using property indices.
 * 
 * The following sample is from the <a href="http://incubator.apache.org/tuscany"
 * target="_blank"> Apache Tuscany</a> project. It was written to help users
 * understand and experiment with SDO. It is based upon example code contained
 * within, and is meant for use with, and reference to the <a
 * href="http://www.osoa.org/download/attachments/791/SDO_Specification_Java_V2.01.pdf?version=1"
 * target="_bank">SDO Specification</a>. In general this sample attempts to use the
 * code and comments contained within the specification, exceptions to this are noted
 * in comments.<br>
 * <br>
 * The following sample is based upon the 'Accessing DataObjects via Property Index'
 * example from the Examples section of the SDO specification. It shows the use of
 * DataObjects and the XMLHelper amd demonstrates accessing the properties of a
 * DataObject using property indices. <br>
 * <br>
 * The following example has the same effect as
 * {@link org.apache.tuscany.samples.sdo.specExampleSection.AccessDataObjectsUsingXPath}.
 * The indexes for the properties are defined as constants in this class. <br>
 * <br>
 * This sample reads an xml file representing a DataObject of a company. In order to
 * create a DataObject or DataGraph this sample relies upon XMLHelper class which is
 * essentially example of a XML DAS implementation. The code shown here would work
 * just as well against an equivalent DataObject that was provided by any DAS. <br>
 * <br>
 * To define the correct Types for each DataObject ( CompanyType, DepartmentType etc )
 * this sample relies upon
 * {@link org.apache.tuscany.samples.sdo.SdoSampleConstants#COMPANY_XSD} which is
 * provided in the resources directory of these samples <br>
 * The xml file
 * {@link org.apache.tuscany.samples.sdo.SdoSampleConstants#COMPANY_DATAOBJECT_XML}
 * used to load the DataObject is also located in this resources directory. To help
 * ensure consistancy with the xsd the xml was generated by
 * {@link org.apache.tuscany.samples.sdo.basic.CreateCompany} which is a good
 * resource for populating DataObjects, or creating DataGraphs dynamically.
 * <br><br>
 * <b>Usage:</b> <br>
 * This sample can easily be run from within Eclipse as a Java Application if tuscany or 
 * the sample-sdo project is imported into Eclipse as an existing project.
 * <br><br>
 * If executing as a standalone application please do the following: 
 * <br>
 * <UL>
 * <LI>Include the following jar files on your classpath :
 * <UL>
 * <LI>SDO API and Tuscany Implementation
 * <UL>
 * <LI>sdo-api-{version}.jar - SDO API
 * <LI>tuscany-sdo-impl-{version}.jar - Tuscany SDO implementation
 * </UL>
 * </LI>
 * <LI>EMF dependencies. 
 * <UL>
 * <LI>emf-common-{version}.jar - some common framework utility and base classes
 * <LI>emf-ecore-{version}.jar - the EMF core runtime implementation classes (the Ecore metamodel)
 * <LI>emf-ecore-change-{version}.jar - the EMF change recorder and framework
 * <LI>emf-ecore-xmi-{version}.jar - EMF's default XML (and XMI) serializer and loader
 * <LI>xsd-{version}.jar - the XML Schema model
 * </UL>
 * </LI>
 * </UL>
 * 
 * These jar files can be obtained by downloading and unpacking a <a href="http://cwiki.apache.org/TUSCANY/sdo-downloads.html" target="_blank">Tuscany binary distribution </a> </LI>
 * <LI>Execute: <br>
 * java org.apache.tuscany.samples.sdo.specExampleSection.AccessingDataObjectsViaPropertyIndex </LI>
 * </UL>
 * 
 * Note: when reading this source code in conjunction with the specification there is scope for confusion
 * over what the values of the numeric indices ought to be,  depending on your understanding of the
 * word "lexical" in the context of sequencing.  So the SDO specification at the 2.0.1 level (and before) says
 * <i>The order of Properties in Type.getDeclaredProperties() is the lexical order of declarations in the
 * XML Schema ComplexType.</i>.  So if your background is in computing and in particular in lexical parsing  
 * then you would understand this to mean "the sequence of tokens produced by the lexical analyis (first) phase of a compiler"
 * and if you ran a lexical parser against the complex type then you would see the elements emerege in the order they were
 * written down.   If however you think more in terms of lexical in the context of lexicons or dictionaries, you may
 * expect the indices to correspond to the lexically sorted (essentially alphabetically sorted) list of element names.
 * After some investigation it is understood that the intention of the spec is to convey the first of these meanings.
 * A clarification in the SDO 2.1 spec is being sought, but may not make it into that document in time.
 * 
 * @author Robbie Minshall
 */

public class AccessingDataObjectsViaPropertyIndex  extends SampleBase {

    HelperContext scope;

    public AccessingDataObjectsViaPropertyIndex(Integer userLevel) {
      super(userLevel, SAMPLE_LEVEL_BASIC);
    }


    /**
     * Predefine the property indexes.
     * 

     */

    private static final int COMPANY_DEPARTMENT = 0;

    private static final int COMPANY_EMPLOYEE_OF_MONTH = 1;

    private static final int COMPANY_NAME = 2;

    private static final int DEPARTMENT_EMPLOYEES = 0;

    private static final int EMPLOYEE_NAME = 0;
    private static final int EMPLOYEE_SN = 1;
    private static final int EMPLOYEE_MANAGER = 2;


    /**
     * Execute this method in order to run the sample.
     * 
     * @param args
     */
    public static void main(String[] args) {
      // TODO make the default level COMMENTARY_FOR_NOVICE, once the rest of the sample has been
      // converted to using commentary()
      AccessingDataObjectsViaPropertyIndex sample = new AccessingDataObjectsViaPropertyIndex(COMMENTARY_FOR_INTERMEDIATE);

      try {
        sample.run();
      }
      catch (Exception e) {
        sample.somethingUnexpectedHasHappened(e);
      }
    }

    public void runSample () throws Exception {
        System.out.println("***************************************");
        System.out.println("SDO Sample AccessingDataObjectsViaPropertyIndex");
        System.out.println("***************************************");
        System.out.println("Demonstrates accessing the properties of a DataObject using property indices.");
        System.out.println("***************************************");

        scope = createScopeForTypes();
        
        // TODO: what happens if not type is defined
        try {
            System.out.println("Defining Types using XSD");
            InputStream is = null;
            is = ClassLoader.getSystemResourceAsStream(SdoSampleConstants.COMPANY_XSD);
            scope.getXSDHelper().define(is, null);
            is.close();
            System.out.println("Type definition completed");
        } catch (Exception e) {
            System.out.println("Exception caught defining types " + e.toString());
            e.printStackTrace();
        }

        try {

            /**
             * In this example simply use a company DataObject read in from xml
             */

            // For DataObjects
            // Obtain the RootObject from the XMLDocument obtained from the XMLHelper
            // instance.
            // In this case the root object is a DataObject representing a company
            DataObject company = scope.getXMLHelper().load(ClassLoader.getSystemResourceAsStream(SdoSampleConstants.COMPANY_DATAOBJECT_XML))
                    .getRootObject();

            // print out some information to show the user what the objects look like
            System.out.println("Company DataObject:");
            System.out.println(company);

            String generatedXml = scope.getXMLHelper().save(company, SdoSampleConstants.COMPANY_NAMESPACE, "company");
            System.out.println("Company data object xml representation: ");
            System.out.println(generatedXml);

            System.out.println("Setting name of company to MegaCorp");
            // Set the "name" property for the company
            company.setString(COMPANY_NAME, "MegaCorp");
            // Get the list of departments
            List departments = company.getList(COMPANY_DEPARTMENT);
            // Get the department at index 0 on the list
            DataObject department = (DataObject) departments.get(0);
            // Get the list of employees for the department
            List employees = department.getList(DEPARTMENT_EMPLOYEES);
            // Get the employee at index 1 on the list
            DataObject employeeFromList = (DataObject) employees.get(2);

            // remove the employee from the graph
            System.out.println("Removing employee " + employeeFromList.getString("name") + " from list of employees");
            employeeFromList.detach();

            // create a new employee
            System.out.println("Creating new employee (manager) Al Smith and adding to list");
            DataObject newEmployee = department.createDataObject(DEPARTMENT_EMPLOYEES);

            /**
             * Properties from Type.getDeclaredProperties, or Type.getProperties
             * should be in lexical (alphanumerical) ordering of the xml schema
             * complex type.
             */

            newEmployee.set(EMPLOYEE_NAME, "Al Smith");
            newEmployee.set(EMPLOYEE_SN, "E0005");
            newEmployee.setBoolean(EMPLOYEE_MANAGER, true);

            System.out.println("setting employee of the month to new employee");
            company.set(COMPANY_EMPLOYEE_OF_MONTH, newEmployee.get(EMPLOYEE_SN));

            // print out some information to show the user what the objects look like
            System.out.println("The modified company DataObject :");
            System.out.println(company);

            generatedXml = scope.getXMLHelper().save(company, SdoSampleConstants.COMPANY_NAMESPACE, "company");
            System.out.println("Company data object xml representation: ");
            System.out.println(generatedXml);

        } catch (Exception e) {
            System.out.println("Sorry there was an error encountered " + e.toString());
            e.printStackTrace();
        }
        System.out.println("GoodBye");

    }
}
