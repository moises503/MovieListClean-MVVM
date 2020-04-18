package uabjo.drti.eleccion.modules.common.framework

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBindingViewHolder<Model>(
    private val variableModelItemId: Int,
    private val binding: ViewDataBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: Model, itemVariables: List<DataBindingVariables>) {
        binding.setVariable(variableModelItemId, item)
        itemVariables.forEach{ variable ->
            binding.setVariable(variable.variableId, variable.variableValue)
        }
        binding.executePendingBindings()
    }
}