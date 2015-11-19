package ian_ellis.kotlinmvvm.presentation.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import ian_ellis.kotlinmvvm.R
import ian_ellis.kotlinmvvm.databinding.ViewToDoItemBinding
import ian_ellis.kotlinmvvm.domain.UiToDoListItem
import ian_ellis.kotlinmvvm.presentation.ToDoSortedList
import ian_ellis.kotlinmvvm.presentation.handlers.SelectedHandler
import ian_ellis.kotlinmvvm.presentation.handlers.SelectedHandler.SelectedListener

import org.jetbrains.anko.layoutInflater

public class ToDosAdapter(val context:Context) : RecyclerView.Adapter<ToDosAdapter.ViewHolder>() {

  private val data = ToDoSortedList(this)

  public var deletedHandler: ((UiToDoListItem) -> Unit)? = null
  public var doneHandler: ((UiToDoListItem) -> Unit)? = null
  public var editHandler: ((UiToDoListItem) -> Unit)? = null

  public fun update(items: List<UiToDoListItem>) {
    data.set(items)
  }

  override fun getItemCount(): Int {
    return data.size()
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(data[position])
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
    return ViewHolder(context.layoutInflater.inflate(R.layout.view_to_do_item, parent, false))
  }

  inner class ViewHolder : RecyclerView.ViewHolder {

    public val binding: ViewToDoItemBinding

    constructor(view: View) : super(view) {
      binding = DataBindingUtil.bind(view)

    }

    public fun bind(item: UiToDoListItem) {
      binding.toDo = item
//      binding.handler = SelectedHandler(item,editHandler)

      binding.handler = SelectedHandler<UiToDoListItem>(item, SelectedListener<UiToDoListItem> { item -> editHandler?.invoke(item) })
    }
  }
}
