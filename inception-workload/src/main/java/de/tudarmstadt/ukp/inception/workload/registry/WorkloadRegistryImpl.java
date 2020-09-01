/*
 * Copyright 2020
 * Ubiquitous Knowledge Processing (UKP) Lab
 * Technische Universität Darmstadt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.tudarmstadt.ukp.inception.workload.registry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import de.tudarmstadt.ukp.clarin.webanno.model.Project;
import de.tudarmstadt.ukp.inception.workload.extension.WorkloadExtension;
import de.tudarmstadt.ukp.inception.workload.model.WorkloadManagementService;

@Component
public class WorkloadRegistryImpl
    implements WorkloadRegistry
{
    private List<WorkloadExtension> extensions;
    private @Autowired WorkloadManagementService workloadManagementService;

    public WorkloadRegistryImpl(
        @Lazy @Autowired(required = false) List<WorkloadExtension> aExtensions)
    {
        extensions = aExtensions;
    }

    @Override
    public List<WorkloadExtension> getExtensions()
    {
        return extensions;
    }

    @Override
    public WorkloadExtension getExtension(String aExtension)
    {
        return extensions.stream().filter(ext -> extensions.equals(ext.getId())).findFirst()
                .orElse(null);
    }

    public String getSelectedExtension(Project aProject)
    {
        return workloadManagementService.getOrCreateExtensionPoint(aProject);
    }

    public void setSelectedExtension(Project aProject, String aExtension)
    {
        workloadManagementService.setExtensionPoint(aExtension,aProject);
    }
}
